import java.time.LocalTime;
import java.util.ArrayList;

/**
 * Starts the scheduler subsystem
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class SchedulerSubsystem {
	
	private static final ArrayList<Integer> lists = new ArrayList<>(){{
		lists.add(1);
		lists.add(2);
		lists.add(3);
	}};

	public static void main(String[] args) {
		
		System.out.println("Time: " + LocalTime.now());
		System.out.println("Scheduler subsystem has started!\n");
		
		// creating a scheduler object
		Thread schedulerThread = new Thread(new Scheduler(lists,7), "Scheduler");
		schedulerThread.start();
	}
}
