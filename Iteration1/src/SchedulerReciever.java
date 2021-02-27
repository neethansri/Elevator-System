
public class SchedulerReciever implements Runnable{

	private Scheduler scheduler;
	
	public SchedulerReciever(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public void run() {
		while(true) {
		scheduler.readElevatorState();
		}
	}	
}
