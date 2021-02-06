/**
 * 
 * @author Solan Siva
 * @author Ben Bagg
 * @author Vijay Ramalingom
 * @author Mohammad Issa
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
