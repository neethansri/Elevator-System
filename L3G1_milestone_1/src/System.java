
public class System {

	public static void main(String[] args) {
		Thread scheduler, elevator, floor;
		
		scheduler = new Thread(new Scheduler(),"Scheduler");
		elevator = new Thread(new Elevator(),"Elevator");
		floor = new Thread(new Floor(), "Floor");
		
		scheduler.start();
		elevator.start();
		floor.start();

	}

}
