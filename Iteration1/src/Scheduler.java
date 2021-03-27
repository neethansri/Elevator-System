import java.time.LocalTime;
import java.util.*;
/**
 * Models the scheduler state machine.
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class Scheduler implements Runnable {
	
	/**
	 * A data structure containing the port numbers of all elevators, and their most recent update (which includes location, direction, etc...)
	 */
	private Map<Integer, ElevatorUpdate> elevators;
	/**
	 * A queue to hold incoming requests from the floor subsystem
	 */
	private Queue<ElevatorMessage> pendingRequests;
	/**
	 * The SchedulerRecieverObject that handles UDP communication for the scheduler subsystem
	 */
	private SchedulerReceiver receiver;
	/**
	 * All the well-known port numbers of the elevators
	 */
	private static final List<Integer> ELEVATOR_PORT_NUMBERS = new ArrayList<>(Arrays.asList(1,2,3,4,5));
	/**
	 * The lowest floor that the elevators can service
	 */
	private static final int BOTTOM_FLOOR = 1;
	/**
	 * The highest floor that the elevators can service
	 */
	private static final int TOP_FLOOR = 7;
	
	private static final int INVALID_ELEVATOR = -1;

	/**
	 * constructor for Scheduler to initialize scheduler object
	 */
	public Scheduler() {
		
		elevators = new HashMap<>();
		pendingRequests = new LinkedList<>();
		
		//initializes the map that keeps track of the elevators with the elevators' initial status
		for(Integer port: ELEVATOR_PORT_NUMBERS) {
			elevators.put(port, ElevatorUpdate.initialStatus());
		}
		
		//creates and runs a SchedulerReceiver object to handle UDP communication
		receiver = new SchedulerReceiver(this);
		Thread receiverThread = new Thread(receiver, "Scheduler Receiver");
		receiverThread.start();
	}
	
	public SchedulerReceiver requestSchedulerReceiver() {
		return receiver;
	}
	
	public String getElevatorMessage() {
		
		return pendingRequests.peek().toString();
	}
				

	/**
	 * Inserts an ElevatorMessage into the scheduler's list of pending requests if it is valid
	 * 
	 * @param em The request to be added to the list of pending requests
	 */
	public void receiveElevatorMessage(ElevatorMessage em) {
		
		//if the request comes from a floor that doesn't exist, it is discarded
		//if the request includes a passenger pressing a car button that doesn't exist, it is discarded
		if(em.getFloor() > TOP_FLOOR || em.getFloor() < BOTTOM_FLOOR || em.getButton() > TOP_FLOOR || em.getButton() < BOTTOM_FLOOR) { 
			System.out.println("Time: " + LocalTime.now());
			System.out.println(Thread.currentThread().getName() + " discarded the incoming request because it referenced a floor that doesn't exist.\n");
			return;
		}
		
		//if the request is valid, add it to the list of pending requests
		synchronized(pendingRequests) {
			pendingRequests.add(em);
			pendingRequests.notifyAll();
		}
	}

	/**
	 * Updates the map that keeps track of elevators with the incoming ElevatorUpdate 
	 * @param eu The most recent update from the elevator
	 * @param port The port number of the elevator
	 */
	public void receiveElevatorUpdate(ElevatorUpdate eu, int port) {
		synchronized(elevators) {
			elevators.replace(port, eu);
		}
		
		//passes the elevator update to the floor, so that the floor lamps can activate if an elevator is approaching
		receiver.sendElevatorUpdate(eu);
	}
	
	/**
	 * Returns the next requests from the list of pending requests
	 * @return The next request
	 */
	private ElevatorMessage getNextPendingRequest() {
		synchronized(pendingRequests) {
			while(pendingRequests.isEmpty()) {
				try {
					pendingRequests.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			return pendingRequests.remove();
		}
	}
	
	/**
	 * Makes a list of the port numbers of elevators going in the right direction to service a request
	 * @param elevators The map associating elevators with their locations, directions, etc...
	 * @param em The request to be serviced
	 * @return A list of the port numbers of elevators going in the right direction to service a request
	 */
	private List<Integer> getElevatorsGoingInRightDirection(Map<Integer, ElevatorUpdate> elevators, ElevatorMessage em){
		List<Integer> elevatorsGoingInRightDirection = new ArrayList<>();
		
		for(Integer port: ELEVATOR_PORT_NUMBERS) {
			ElevatorUpdate eu = elevators.get(port);
			
			//if the elevator is in emergency mode, do not assign it
			if(eu.isInEmergency()) continue;
			
			if(em.getDirection() == ElevatorDirection.DOWN) {
				if(eu.getFloor() >= em.getFloor() && eu.getDirection() == ElevatorDirection.DOWN) {
					elevatorsGoingInRightDirection.add(port);
				}
			}
			else if(em.getDirection() == ElevatorDirection.UP) {
				if(eu.getFloor() <= em.getFloor() && eu.getDirection() == ElevatorDirection.UP) {
					elevatorsGoingInRightDirection.add(port);
				}
			}
		}
		return elevatorsGoingInRightDirection;
	}
	
	/**
	 * Returns the elevator out of a specified list that has the least passengers
	 * @param elevators The map associating elevators with their locations, passengers, etc...
	 * @param suitableElevators The list of elevator port numbers to be chosen from
	 * @return The port number of the chosen elevator
	 */
	private int getElevatorWithLeastPassengers(Map<Integer, ElevatorUpdate> elevators, List<Integer> suitableElevators) {
		int leastPassengersPort = INVALID_ELEVATOR;
		for(Integer port: suitableElevators) {
			
			ElevatorUpdate eu = elevators.get(port);
			
			//if the elevator is in emergency mode, do not assign it
			if(eu.isInEmergency()) continue;
			
			if(leastPassengersPort == INVALID_ELEVATOR) leastPassengersPort = port;
			else {
				if(eu.getPassengers() < elevators.get(leastPassengersPort).getPassengers()) {
					leastPassengersPort = port;
				}
			}
		}
		return leastPassengersPort;
	}
	
	/**
	 * Chooses an elevator to service a given request.
	 * @param em The request to be serviced
	 */
	private void assignElevatorToRequest(ElevatorMessage em) {
		
		//creates a copy of the elevator map so the critical section can be short
		Map<Integer, ElevatorUpdate> elevatorMapCopy;
		synchronized(elevators) {
			elevatorMapCopy = Map.copyOf(elevators);
		}
		
		//creates a list of all elevators going in the right direction to service the request
		List<Integer> elevatorsGoingInRightDirection = getElevatorsGoingInRightDirection(elevatorMapCopy, em);
		
		int chosenElevator;
		if(!elevatorsGoingInRightDirection.isEmpty()) {
			//if there are elevators going in the right direction, choose the one with the least passengers out of those elevators
			chosenElevator = getElevatorWithLeastPassengers(elevatorMapCopy, elevatorsGoingInRightDirection);
		}
		else {
			//if there are no elevators going in the right direction, choose the elevator with the least passengers out of all elevators
			chosenElevator = getElevatorWithLeastPassengers(elevatorMapCopy, ELEVATOR_PORT_NUMBERS);
		}
		
		if(chosenElevator == INVALID_ELEVATOR) {
			//no elevator available to service the request
		}
		
		else {
			//sends the request to the chosen elevator
			receiver.sendElevatorMessage(em, chosenElevator);
		}
	}
	
	/**
	 * Continuously processes incoming requests from the floor subsystem
	 */
	public void processRequests() {
		while(true) {
			assignElevatorToRequest(getNextPendingRequest());
		}
	}
	
	@Override
	/**
	 * run method in Scheduler thread used to handle the events from from floor to
	 * elevator
	 */
	public void run() {
		processRequests();
	}

}
