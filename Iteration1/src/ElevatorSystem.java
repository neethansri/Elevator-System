/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class ElevatorSystem {

	public static void main(String[] args) {

		// creating a scheduler object
		Scheduler scheduler = new Scheduler();
		Elevator elevator = new Elevator(scheduler);

		// creating the threads
		Thread Floor = new Thread(new Floor(scheduler), "Floor");
		Thread Scheduler = new Thread(scheduler, "Scheduler");
		Thread Elevator = new Thread(elevator, "Elevator");
		Thread ElevatorReciever = new Thread(new ElevatorReciever(elevator, scheduler), "ElevatorReciever");
		Thread SchedulerReciever = new Thread(new SchedulerReciever(scheduler), "SchedulerReciever");

		System.out.println("The Elevator System has Started!\n");
		
		// starting the threads
		Floor.start();
		Elevator.start();
		Scheduler.start();
		ElevatorReciever.start();
		SchedulerReciever.start();

	}

}
