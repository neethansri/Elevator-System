import java.time.LocalTime;

public class ElevatorReciever implements Runnable{
	
	private Elevator elevator;
	private Scheduler scheduler;

	public ElevatorReciever(Elevator elevator, Scheduler scheduler) {
		this.elevator = elevator;
		this.scheduler = scheduler;
	}
	
	@Override
	public void run() {
		while(true) {
			ElevatorMessage message = scheduler.getEvent();
			System.out.println("Time: " + LocalTime.now());
			System.out.println(Thread.currentThread().getName() + " recieved " + message + " and added the request to the elevator's list\n");
			elevator.addFloorToVisit(message);
		}
		
	}
	
}
