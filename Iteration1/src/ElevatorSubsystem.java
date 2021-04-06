import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Starts all elevators in the elevator subsystem
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class ElevatorSubsystem {
	
	private static final List<Integer> ELEVATOR_PORT_NUMBERS = new ArrayList<>(Arrays.asList(1,2,3,4,5));

	public static void main(String[] args) {
		
		System.out.println("Time: " + LocalTime.now());
		System.out.println("Elevator subsystem has started!\n");

		for(Integer port: ELEVATOR_PORT_NUMBERS) {
			Thread thread = new Thread(new Elevator(port), "Elevator " + port);
			thread.start();
		}
	}

}
