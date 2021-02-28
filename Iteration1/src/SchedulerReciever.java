/**
 * Thread in the Scheduler subsystem that recieves updates from the elevators and notifies the scheduler
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class SchedulerReciever implements Runnable{

	/**
	 * The scheduler associated with this reciever
	 */
	private Scheduler scheduler;
	
	/**
	 * Constructor of class SchedulerReciever
	 * @param scheduler The scheduler associated with this reciever
	 */
	public SchedulerReciever(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	/**
	 * Continuously checks for updates from elevators and notifies the scheduler
	 */
	@Override
	public void run() {
		while(true) {
		scheduler.readElevatorState();
		}
	}	
}
