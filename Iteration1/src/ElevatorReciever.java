import java.time.LocalTime;

/**
 * A thread in the Elevator subsystem that recieves messages from the scheduler and updates the elevator's list
 * @author benba
 *
 */
public class ElevatorReciever implements Runnable{
	
	/**
	 * The elevator associated with this reciever
	 */
	private Elevator elevator;
	/**
	 * The scheduler associated with the elevator
	 */
	private Scheduler scheduler;

	/**
	 * Constructor for class ElevatorReciever
	 * @param elevator
	 * @param scheduler
	 */
	public ElevatorReciever(Elevator elevator, Scheduler scheduler) {
		this.elevator = elevator;
		this.scheduler = scheduler;
	}
	
	/**
	 * Continuously gets messages from the scheduler and notifies the elevator
	 */
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
