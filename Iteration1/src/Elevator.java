
public class Elevator implements Runnable {
	
	public static final int INITIAL_FLOOR = 1;
	
	private Scheduler scheduler;
	private ElevatorDirection direction;
	private int floor;
	
	public Elevator(Scheduler scheduler) {
		this.scheduler = scheduler;
		direction = ElevatorDirection.STOP;
		floor = INITIAL_FLOOR;
	}

	@Override
	public void run() {
	    while(true) {
		scheduler.getEvent();
		}
	}
	

	
}
