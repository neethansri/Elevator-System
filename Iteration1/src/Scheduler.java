/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */

import java.util.ArrayList;

public class Scheduler implements Runnable {
	
	//initializing the array used to hold events
	private ArrayList<ElevatorMessage> fromFloor, toFloor, fromElevator, toElevator;
	private String floorTest, elevatorTest, schedulerTest;
	
	/**
	 * constructor for Scheduler to initialize scheduler object 
	 */
	public Scheduler() {
		fromFloor = new ArrayList<>();
		toFloor = new ArrayList<>();
		fromElevator = new ArrayList<>();
		toElevator = new ArrayList<>();
	}
	
	public String getFloorTest() {
		return floorTest;
	}
	public String getElevatorTest() {
		return elevatorTest;
	}
	public String getSchedulerTest() {
		return schedulerTest;
	}

	/**
	 * the floor thread calls this method to send messages to the scheduler
	 * @param floorinfo type ElevatorMessage
	 */
	public synchronized void sendEvent(ElevatorMessage floorInfo) {
		//adding floorInfo to the fromFloor arrayList
		fromFloor.add(floorInfo);
		System.out.println(Thread.currentThread().getName() + " SENT " + floorInfo);
		floorTest = "Floor SENT " + floorInfo;
		notifyAll(); // notifyAll threads that are not awake
	}
	
	
	/**
	 * the elevator thread calls this method to read messages from the scheduler and send them back
	 */
	public synchronized void getEvent() {
		//while toElevator is empty
		while(toElevator.isEmpty() == true) {
			try {
				wait(); //wait for it to fill
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		fromElevator.add(toElevator.get(0));
		System.out.println(Thread.currentThread().getName() + " RECEIVED AND SENT BACK " + toElevator.get(0));
		elevatorTest = "Elevator RECEIVED AND SENT BACK " + toElevator.get(0);
		toElevator.remove(0);
		notifyAll(); //notify threads that are not awake
	}
	

	/**
	 * the floor thread calls this method to read messages from the scheduler
	 */
	public synchronized void receiveEvent() {
		//while toFloor array is empty
		while(toFloor.isEmpty() == true) {
			try {
				wait(); //wait for it to fill
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " RECEIVED " + toFloor.get(0)); 
		toFloor.remove(0);
		notifyAll(); //notify threads that are not awake
	}
	
	
	/**
	 * the scheduler thread passes messages from floor to elevator
	 */
	public synchronized void handleFloorMessages() {
		//while fromFloor array is empty
		while(fromFloor.isEmpty()) {
			try {
				wait(); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		toElevator.add(fromFloor.get(0));
		System.out.println(Thread.currentThread().getName() + " PASSED " + fromFloor.get(0) + " FROM FLOOR TO ELEVATOR");
		fromFloor.remove(0);
		notifyAll(); //notify threads that are not awake
	}
	
	//the scheduler thread passes messages from elevator to floor
	public synchronized void handleElevatorMessages() {
		// while fromElevator is empty
		while(fromElevator.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		toFloor.add(fromElevator.get(0)); // add to the array for handling ElevatorMessage
		System.out.println(Thread.currentThread().getName() + " PASSED " + fromElevator.get(0) + " FROM ELEVATOR TO FLOOR");
		schedulerTest = "Scheduler PASSED " + fromElevator.get(0) + " FROM ELEVATOR TO FLOOR";
		fromElevator.remove(0);
		notifyAll(); // notify all threads that are not awake
	}
 
	@Override
	/**
	 * run method in Scheduler thread used to handle the events from from floor to elevator and elevator to floor
	 */
	public void run() {
		while(true) {
			handleFloorMessages();
			handleElevatorMessages();
		}
	}

}
