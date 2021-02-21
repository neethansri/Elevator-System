/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class Elevator implements Runnable {

	// initializing variables
	public static final int INITIAL_FLOOR = 1;

	private Scheduler scheduler;
	private ElevatorDirection direction;
	private int floor;

	/**
	 * Elevator constructor takes in scheduler object and initialized the elevator
	 * object
	 * 
	 * @param scheduler
	 */
	public Elevator(Scheduler scheduler) {
		this.scheduler = scheduler;
		direction = ElevatorDirection.STOP;
		floor = INITIAL_FLOOR;
	}

	/**
	 * Run method in Elevator thread used to getEvent from scheduler thread
	 */
	@Override
	public void run() {
		while (true) {
			scheduler.getEvent(); // gets event form scheduler
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
			}
		}
	}

}
