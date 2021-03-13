import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */

public class ElevatorMessage {

	// Initializing variables
	private String time;
	private int floor;
	private int button;
	private ElevatorDirection direction;
	
	private static final int ELEVATOR_MESSAGE_SIGNATURE = 2;
	public static final byte SPACER = 0;

	/**
	 * ElevatorMessage constructor used to initialize ElevatorMessage object
	 * 
	 * @param time the time that the message was generated
	 * @param floor the floor button that was pressed
	 * @param dir the direction button that was pressed
	 * @param button the car button that was pressed
	 */
	public ElevatorMessage(String time, int floor, String dir, int button) {
		this.time = time;
		this.floor = floor;
		this.button = button;
		this.direction = convertDirection(dir);

	}
	
	public ElevatorMessage(byte[] array) {
		List<Integer> spacerIndexes = new ArrayList<>();
		for(int i = 0; i < array.length; i++) {
			if(array[i] == SPACER) spacerIndexes.add(i);
		}
		if(spacerIndexes.size() == 3) {
			
			floor = Integer.parseInt(new String(Arrays.copyOfRange(array, 1, spacerIndexes.get(0))));
			button = Integer.parseInt(new String(Arrays.copyOfRange(array, spacerIndexes.get(0) + 1, spacerIndexes.get(1))));
			direction = Integer.parseInt(new String(Arrays.copyOfRange(array, spacerIndexes.get(1) + 1, spacerIndexes.get(2)))) == 1? ElevatorDirection.UP: ElevatorDirection.DOWN;
			time = new String(Arrays.copyOfRange(array, spacerIndexes.get(2) + 1, array.length));
		}
		else {
			//invalid byte array
			return;
		}
	}

	/**
	 * getter function to get Time
	 * 
	 * @return string showing the time
	 */
	public String getTime() {
		return time;
	}

	/**
	 * getter function to get floor
	 * 
	 * @return int value showing floor number
	 */
	public int getFloor() {
		return floor;
	}

	/**
	 * getter function to get the floor were going to
	 * 
	 * @return int value for the floor were heading to
	 */
	public int getButton() {
		return button;
	}

	/**
	 * convertDirection is used to convert the string value of the direction of the
	 * elevator to a corresponding enum
	 * 
	 * @param dir String value that comes from the file we read in
	 * @return the corresponding enum for the direction of the elevator
	 */
	public ElevatorDirection convertDirection(String dir) {
		// if cases to cover all the cases
		if (dir.equals("UP")) {
			direction = ElevatorDirection.UP;
		} else if (dir.equals("DOWN")) {
			direction = ElevatorDirection.DOWN;
		} else {
			direction = ElevatorDirection.STOPPED;
		}
		return direction;
	}

	/**
	 * getter function used get the direction of the elevator
	 * 
	 * @return the enum value of direction
	 */
	public ElevatorDirection getDirection() {
		return direction;
	}

	/**
	 * toString method used to return the string representation of the object
	 * 
	 * @return the string representation of the object
	 */
	public String toString() {
		return time + " " + floor + " " + direction + " " + button;
	}
	
	public byte[] toByteArray() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		try {
			stream.write(ELEVATOR_MESSAGE_SIGNATURE);
			stream.write(String.valueOf(floor).getBytes());
			stream.write(SPACER);
			stream.write(String.valueOf(button).getBytes());
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
