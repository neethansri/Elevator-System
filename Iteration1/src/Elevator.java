/**
 * 
 * @author Solan Siva
 * @author Ben Bagg
 * @author Vijay Ramalingom
 * @author Mohammad Issa
 * @author Neethan Sriranganathan 101082581
 */
public class Elevator implements Runnable {
	
	//initializing variables
	public static final int INITIAL_FLOOR = 1;
	
	private Scheduler scheduler;
	private ElevatorDirection direction;
	private int floor;
	
	/**
	 * Elevator constructor takes in scheduler object and initialized the elevator object
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
	    while(true) {
		scheduler.getEvent(); // gets event form scheduler
		}
	}
	

	
}
