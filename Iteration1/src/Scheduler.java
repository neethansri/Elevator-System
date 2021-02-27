/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */

import java.util.ArrayList;
import java.util.List;
import java.time.LocalTime;

public class Scheduler implements Runnable {

	// initializing the array used to hold events
	private List<ElevatorMessage> fromFloor, toFloor, fromElevator, toElevator;
	private String floorTest, elevatorTest, schedulerTest;
	
	private List<ElevatorUpdate> pendingElevatorUpdates;
	
	private SchedulerState currentState;

	/**
	 * constructor for Scheduler to initialize scheduler object
	 */
	public Scheduler() {
		fromFloor = new ArrayList<>();
		toFloor = new ArrayList<>();
		fromElevator = new ArrayList<>();
		toElevator = new ArrayList<>();
		pendingElevatorUpdates = new ArrayList<>();
		currentState = SchedulerState.IDLE;
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
	public synchronized void sendEvent(ElevatorMessage floorInfo) {
		// adding floorInfo to the fromFloor arrayList
		fromFloor.add(floorInfo);
		System.out.println("Time: " + LocalTime.now());
		System.out.println(
				"The " + Thread.currentThread().getName() + " Subsystem sent: " + floorInfo + ", to the scheduler \n");
		floorTest = "Floor SENT " + floorInfo;
		notifyAll(); // notifyAll threads that are not awake
	}

	/**
	 * the elevator thread calls this method to read messages from the scheduler and
	 * send them back
	 */
	public synchronized ElevatorMessage getEvent() {
		// while toElevator is empty
		while (toElevator.isEmpty() == true) {
			try {
				wait(); // wait for it to fill
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		ElevatorMessage message = toElevator.get(0);
		toElevator.remove(0);
		notifyAll();
		return message;
	}

	/**
	 * the floor thread calls this method to read messages from the scheduler
	 */
	public synchronized void receiveEvent() {
		// while toFloor array is empty
		while (toFloor.isEmpty() == true) {
			try {
				wait(); // wait for it to fill
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Time: " + LocalTime.now());
		System.out.println("The " + Thread.currentThread().getName() + " Subsystem received: " + toFloor.get(0)
				+ ", from the scheduler \n");
		toFloor.remove(0);
		notifyAll(); // notify threads that are not awake
	}
	
	public synchronized void updateElevatorState(ElevatorUpdate eu) {
		pendingElevatorUpdates.add(eu);
		notifyAll();
	}
	
	public synchronized void readElevatorState() {
		while(pendingElevatorUpdates.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		ElevatorUpdate eu = pendingElevatorUpdates.get(0);
		pendingElevatorUpdates.remove(0);
		System.out.println("Time: " + LocalTime.now());
		System.out.println(Thread.currentThread().getName() + " has been notified that the elevator is at floor " + eu.getFloor() + ""
				+ " and has direction " + eu.getDirection() + "\n");
		notifyAll();
	}

	/**
	 * the scheduler thread passes messages from floor to elevator
	 */
	public synchronized void handleFloorMessages() {
		// while fromFloor array is empty
		while (fromFloor.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		toElevator.add(fromFloor.get(0));
		System.out.println("Time: " + LocalTime.now());
		System.out.println("The " + Thread.currentThread().getName() + " Subsystem passed: " + fromFloor.get(0)
				+ ", from the floor to the elevator \n");
		fromFloor.remove(0);
		notifyAll(); // notify threads that are not awake
	}

	// the scheduler thread passes messages from elevator to floor
	public synchronized void handleElevatorMessages() {
		// while fromElevator is empty
		while (fromElevator.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		toFloor.add(fromElevator.get(0)); // add to the array for handling ElevatorMessage
		System.out.println("Time: " + LocalTime.now());
		System.out.println("The " + Thread.currentThread().getName() + " Subsystem passed: " + fromElevator.get(0)
				+ ", from the elevator to the floor \n");
		schedulerTest = "Scheduler PASSED " + fromElevator.get(0) + " FROM ELEVATOR TO FLOOR";
		fromElevator.remove(0);
		notifyAll(); // notify all threads that are not awake
	}

	@Override
	/**
	 * run method in Scheduler thread used to handle the events from from floor to
	 * elevator and elevator to floor
	 */
	public void run() {
		while (true) {
			handleFloorMessages();
		}
	}

}
