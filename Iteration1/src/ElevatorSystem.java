
public class ElevatorSystem {

	public static void main(String[] args) {
		
		
		Thread Floor = new Thread(new  Floor(),"Floor");
		Thread Scheduler = new Thread(new  Scheduler(),"Scheduler");
		Thread Elevator = new Thread(new Elevator(),"Elevator");
		
		//Scheduler.start();
		Floor.start();
		//Elevator.start();

	}

}
