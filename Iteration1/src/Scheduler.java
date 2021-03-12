import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;
import java.util.*;
/**
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class Scheduler implements Runnable {
	private DatagramSocket receiveSocket, socket;
	private DatagramPacket recievedPacket, ackPacket;

	private List<ElevatorMessage> fromFloor, toElevator;
	private String floorTest, elevatorTest, schedulerTest;
	private ElevatorMessage floorinfo;
	private List<ElevatorUpdate> pendingElevatorUpdates;
	
	private SchedulerState currentState;

	/**
	 * constructor for Scheduler to initialize scheduler object
	 */
	public Scheduler() {
		fromFloor = new ArrayList<>();
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
	
	public List<ElevatorMessage> getfromFloor(){
		return fromFloor;
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

	public synchronized void updateElevatorState(ElevatorUpdate eu) {
		pendingElevatorUpdates.add(eu);
		currentState = SchedulerState.PENDING;
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
		
		if(pendingElevatorUpdates.isEmpty()) currentState = SchedulerState.IDLE;
		
		System.out.println("Time: " + LocalTime.now());
		System.out.println(Thread.currentThread().getName() + " has been notified that the elevator is at floor " + eu.getFloor() + ", has "
				+ eu.getPassengers() + " passengers, and has direction "
				+ eu.getDirection() + "\n");
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
