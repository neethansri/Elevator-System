
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * A message class used by the elevator subsystem to send updates about its location and direction to the scheduler
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class ElevatorUpdate{
	
	public static final byte ELEVATOR_UPDATE_SIGNATURE = 1;
	public static final byte SPACER = 0;
	
	/**
	 * The floor that the elevator will initially start at
	 */
	public static final int INITIAL_FLOOR = 1;
	
	/**
	 * The current floor of the elevator
	 */
	private int floor;
	/**
	 * The current direction of the elevator
	 */
	private ElevatorDirection direction;
	/**
	 * The time that the elevator sent the update
	 */
	private String time;
	
	private int passengers;
	
	/**
	 * Constructor of class ElevatorUpdate
	 * @param floor The current floor of the elevator
	 * @param direction The current direction of the elevator
	 * @param time The time that the elevator sent the update
	 */
	public ElevatorUpdate(int floor, ElevatorDirection direction, int passengers, String time) {
		this.floor = floor;
		this.direction = direction;
		this.time = time;
		this.passengers = passengers;
	}
	
	public ElevatorUpdate(byte[] array) {
		List<Integer> spacerIndexes = new ArrayList<>();
		for(int i = 0; i < array.length; i++) {
			if(array[i] == SPACER) spacerIndexes.add(i);
		}
		if(spacerIndexes.size() == 3) {
			floor = Integer.parseInt(new String(Arrays.copyOfRange(array, 1, spacerIndexes.get(0))));
			passengers = Integer.parseInt(new String(Arrays.copyOfRange(array, spacerIndexes.get(0) + 1, spacerIndexes.get(1))));
			direction = Integer.parseInt(new String(Arrays.copyOfRange(array, spacerIndexes.get(1) + 1, spacerIndexes.get(2)))) == 1? ElevatorDirection.UP: ElevatorDirection.DOWN;
			time = new String(Arrays.copyOfRange(array, spacerIndexes.get(2) + 1, array.length));
		}
		else {
			//invalid byte array
			return;
		}
	}
	
	public static ElevatorUpdate initialStatus() {
		return new ElevatorUpdate(INITIAL_FLOOR, ElevatorDirection.STOPPED, 0, LocalTime.now().toString());
	}

	/**
	 * getter for floor
	 * @return The current floor of the elevator
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * getter for direction
	 * @return The current direction of the elevator
	 */
	public ElevatorDirection getDirection() {
		return direction;
	}
	
	public int getPassengers() {
		return passengers;
	}

	/**
	 * getter for time
	 * @return The time that the elevator sent the update
	 */
	public String getTime() {
		return time;
	}
	
	/**
	 * formats the contents of the message as a string
	 */
	public String toString() {
		return time + " " + floor + " " + passengers + " " + direction;
	}
	
	public byte[] toByteArray() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			stream.write(ELEVATOR_UPDATE_SIGNATURE);
			stream.write(String.valueOf(floor).getBytes());
			stream.write(SPACER);
			stream.write(String.valueOf(passengers).getBytes());
			stream.write(SPACER);
			stream.write(String.valueOf(direction == ElevatorDirection.UP? 1: -1).getBytes());
			stream.write(SPACER);
			stream.write(time.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream.toByteArray();
	}
}
