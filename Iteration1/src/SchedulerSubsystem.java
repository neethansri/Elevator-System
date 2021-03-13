import java.time.LocalTime;

/**
 * Starts the scheduler subsystem
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class SchedulerSubsystem {

	public static void main(String[] args) {
		
		System.out.println("Time: " + LocalTime.now());
		System.out.println("Scheduler subsystem has started!\n");
		
		// creating a scheduler object
		Thread schedulerThread = new Thread(new Scheduler(), "Scheduler");
		schedulerThread.start();
	}
}
