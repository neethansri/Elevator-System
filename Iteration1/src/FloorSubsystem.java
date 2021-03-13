import java.time.LocalTime;

/**
 * Starts the floor subsystem
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class FloorSubsystem {
	
	private static final int FLOOR_PORT = 100;

	public static void main(String[] args) {
		
		System.out.println("Time: " + LocalTime.now());
		System.out.println("Floor subsystem has started!\n");
		
		//creating a floor object
		Thread floorThread = new Thread(new Floor(FLOOR_PORT), "Floor");
		floorThread.start();
	}

}
