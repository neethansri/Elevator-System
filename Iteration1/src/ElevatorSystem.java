/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class ElevatorSystem {

	public static void main(String[] args) {
		
		
		//creating a scheduler object
		Scheduler scheduler = new Scheduler();
		
		
		//creating the threads
		Thread Floor = new Thread(new Floor(scheduler),"Floor");
		Thread Scheduler = new Thread(scheduler, "Scheduler");
		Thread Elevator = new Thread(new Elevator(scheduler),"Elevator");
		
		//starting the threads
		Floor.start();
		Elevator.start();
		Scheduler.start();
		

	}

}
