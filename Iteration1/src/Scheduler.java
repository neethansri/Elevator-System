import java.util.ArrayList;

public class Scheduler implements Runnable {

	private ArrayList<ElevatorMessage> fromFloor, toFloor, fromElevator, toElevator;
	private ElevatorMessage testVariable;
	
	public Scheduler() {
		fromFloor = new ArrayList<>();
		toFloor = new ArrayList<>();
		fromElevator = new ArrayList<>();
		toElevator = new ArrayList<>();
	}
	
	public String getTest() {
		return testVariable.toString();
	}

	//the floor thread calls this method to send messages to the scheduler
	public synchronized void sendEvent(ElevatorMessage floorInfo) {
		fromFloor.add(floorInfo);
		System.out.println(Thread.currentThread().getName() + " SENT " + floorInfo);
		notifyAll();
	}
 
	//the elevator thread calls this method to read messages from the scheduler and send them back
	public synchronized void getEvent() {
		while(toElevator.isEmpty() == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		fromElevator.add(toElevator.get(0));
		testVariable = toElevator.get(0);
		System.out.println(Thread.currentThread().getName() + " RECEIVED AND SENT BACK " + toElevator.get(0));
		toElevator.remove(0);
		notifyAll();
	}
	
	//the floor thread calls this method to read messages from the scheduler
	public synchronized void receiveEvent() {
		while(toFloor.isEmpty() == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(Thread.currentThread().getName() + " RECEIVED " + toFloor.get(0));
		toFloor.remove(0);
		notifyAll();
	}
	
	//the scheduler thread passes messages from floor to elevator
	private synchronized void handleFloorMessages() {
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
		notifyAll();
	}
	
	//the scheduler thread passes messages from elevator to floor
	private synchronized void handleElevatorMessages() {
		while(fromElevator.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		toFloor.add(fromElevator.get(0));
		System.out.println(Thread.currentThread().getName() + " PASSED " + fromElevator.get(0) + " FROM ELEVATOR TO FLOOR");
		fromElevator.remove(0);
		notifyAll();
	}
 
	@Override
	public void run() {
		while(true) {
			handleFloorMessages();
			handleElevatorMessages();
		}
	}

}
