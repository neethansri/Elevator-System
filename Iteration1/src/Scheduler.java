import java.util.ArrayList;
import java.util.List;
import java.util.*;
/**
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class Scheduler implements Runnable {
	
	private Map<Integer, ElevatorUpdate> elevators;
	
	private Queue<ElevatorMessage> pendingRequests;
	
	private SchedulerReciever receiver;
	
	private static final List<Integer> ELEVATOR_PORT_NUMBERS = new ArrayList<>(Arrays.asList(1,2,3,4,5));

	private String floorTest, elevatorTest, schedulerTest;


	/**
	 * constructor for Scheduler to initialize scheduler object
	 */
	public Scheduler() {
		
		elevators = new HashMap<>();
		
		pendingRequests = new LinkedList<>();
		
		for(Integer port: ELEVATOR_PORT_NUMBERS) {
			elevators.put(port, ElevatorUpdate.initialStatus());
		}
		
		receiver = new SchedulerReciever(this);
		
		Thread receiverThread = new Thread(receiver, "Scheduler Receiver");
		receiverThread.start();
	}
				
	/**
	 * 
	 * @return the message sent from the floor to the scheduler
	 */
	public String getFloorTest() {
		return floorTest;
	}

	/**
	 * 
	 * @return the message received by the elevator and sent back to the scheduler
	 */
	public String getElevatorTest() {
		return elevatorTest;
	}

	/**
	 * 
	 * @return the message received from the elevator by the scheduler and sent to
	 *         the floor
	 */
	public String getSchedulerTest() {
		return schedulerTest;
	}

	/**
	 * the floor thread calls this method to send messages to the scheduler
	 * 
	 * @param floorinfo type ElevatorMessage
	 */
	public void receiveElevatorMessage(ElevatorMessage em) {
		synchronized(pendingRequests) {
			pendingRequests.add(em);
			pendingRequests.notifyAll();
		}
	}

	public void receiveElevatorUpdate(ElevatorUpdate eu, int port) {
		synchronized(elevators) {
			elevators.replace(port, eu);
		}
	}
	
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
	 * Chooses an elevator to service a given request.
	 * @param em The request to be serviced
	 */
	private void assignElevatorToRequest(ElevatorMessage em) {
		
		synchronized(elevators) {
			//here's where you access the map containing elevator numbers, locations, directions, etc...
		}
		
		//as of now, a random elevator is chosen for each request
		Random r = new Random();
		int chosenElevator = ELEVATOR_PORT_NUMBERS.get(r.nextInt(ELEVATOR_PORT_NUMBERS.size()));
		
		receiver.sendElevatorMessage(em, chosenElevator);
	}
	
	public void processRequests() {
		while(true) {
			assignElevatorToRequest(getNextPendingRequest());
		}
	}
	
	@Override
	/**
	 * run method in Scheduler thread used to handle the events from from floor to
	 * elevator and elevator to floor
	 */
	public void run() {
		processRequests();
	}

}
