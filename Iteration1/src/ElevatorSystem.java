import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Executes a test case for the current project iteration
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class ElevatorSystem {
	
	private static final List<Integer> ELEVATOR_PORT_NUMBERS = new ArrayList<>(Arrays.asList(1,2,3,4,5));
	
	private static final int FLOOR_PORT = 100;

	public static void main(String[] args) {
		
		//creating a floor object
		Thread floorThread = new Thread(new Floor(FLOOR_PORT), "Floor");

		// creating a scheduler object
		Thread schedulerThread = new Thread(new Scheduler(), "Scheduler");
		
		for(Integer port: ELEVATOR_PORT_NUMBERS) {
			Thread thread = new Thread(new Elevator(port), "Elevator " + port);
			thread.start();
		}

		schedulerThread.start();
		floorThread.start();
	}

}
