
public class ElevatorSystem {

	public static void main(String[] args) {
		
		Scheduler scheduler = new Scheduler();
		
		Thread Floor = new Thread(new Floor(scheduler),"Floor");
		Thread Scheduler = new Thread(scheduler, "Scheduler");
		Thread Elevator = new Thread(new Elevator(scheduler),"Elevator");
	
		Floor.start();
		Elevator.start();
		Scheduler.start();
		

	}

}
