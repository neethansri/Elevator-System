import java.util.List;
import java.util.ArrayList;

public class Scheduler implements Runnable {

	private ArrayList<ElevatorMessage> fromFloor = new ArrayList<ElevatorMessage>();
	private ArrayList<ElevatorMessage> toFloor = new ArrayList<ElevatorMessage>();
	private ArrayList<ElevatorMessage> fromElevator = new ArrayList<ElevatorMessage>();
	private ArrayList<ElevatorMessage> toElevator = new ArrayList<ElevatorMessage>();

	//the floor thread calls this method to send messages to the scheduler
	public synchronized void sendEvent(ElevatorMessage floorinfo) {
		fromFloor.add(floorinfo);
		System.out.println("FLOOR SENT " + floorinfo);
		notifyAll();
	}
 
	//the elevator thread calls this method to read messages from the scheduler and send them back
	public synchronized void getEvent() {
		while(toElevator.isEmpty() == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		fromElevator.addAll(toElevator); 
		toElevator.clear();
		System.out.println("ELEVATOR RECIEVED AND SENT BACK" + toElevator);
		notifyAll();
	}
	
	//the floor thread calls this method to read messages from the scheduler
	public synchronized void recieveEvent() {
		while(toFloor.isEmpty() == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("FLOOR RECIEVED " + toFloor);
		toFloor.clear();
		notifyAll();
	}
	
	//the scheduler thread passes messages from floor to elevator
	private synchronized void handleFloorMessages() {
		while(fromFloor.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		toElevator.addAll(fromFloor);
		System.out.println("SCHEDULER PASSED " + fromFloor + " FROM FLOOR TO ELEVATOR");
		fromFloor.clear();
		notifyAll();
	}
	
	//the scheduler thread passes messages from elevator to floor
	private synchronized void handleElevatorMessages() {
		while(fromElevator.isEmpty()) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		toFloor.addAll(fromElevator);
		System.out.println("SCHEDULER PASSED " + fromElevator + " FROM ELEVATOR TO FLOOR");
		fromElevator.clear();
		notifyAll();
	}
	
	/*public String sendConfirmation() {
		return "The elevator successfully stopped at" + requests() ;
	}*/
		
	/*public ArrayList<ElevatorMessage> requests(){
		return previousrequest;
	}
	
	public boolean getFlag() {
		return flag;
	}*/
 
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true) {
			handleFloorMessages();
			handleElevatorMessages();
		}
	}

}
