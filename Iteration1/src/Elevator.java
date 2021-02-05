
public class Elevator implements Runnable {
	Scheduler scheduler;
	
	public Elevator(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	@Override
	public void run() {
	    while(true) {
		scheduler.getEvent();
		}
	}
	

	
}
