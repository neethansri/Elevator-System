import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
	private Fault fault;
	private boolean noMoreRequests;
	
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
	public ElevatorMessage(String time, int floor, String dir, int button, String f, boolean noMoreRequests) {
		this.time = time;
		this.floor = floor;
		this.button = button;
		this.direction = convertDirection(dir);
		this.fault = convertFault(f);
		this.noMoreRequests = noMoreRequests;
	}
	
	public ElevatorMessage(String time, int floor, ElevatorDirection dir, int button) {
		this.time = time;
		this.floor = floor;
		this.button = button;
		this.direction = dir;
		this.noMoreRequests = false;
	}
	
	public ElevatorMessage(byte[] array) {
		List<Integer> spacerIndexes = new ArrayList<>();
		for(int i = 0; i < array.length; i++) {
			if(array[i] == SPACER) spacerIndexes.add(i);
		}
		if(spacerIndexes.size() == 5) {
			
			floor = Integer.parseInt(new String(Arrays.copyOfRange(array, 1, spacerIndexes.get(0))));
			button = Integer.parseInt(new String(Arrays.copyOfRange(array, spacerIndexes.get(0) + 1, spacerIndexes.get(1))));
			direction = Integer.parseInt(new String(Arrays.copyOfRange(array, spacerIndexes.get(1) + 1, spacerIndexes.get(2)))) == 1? ElevatorDirection.UP: ElevatorDirection.DOWN;
			
			
			//assigns the correct fault (if any) to happen when servicing the request
			int faultInt = Integer.parseInt(new String(Arrays.copyOfRange(array, spacerIndexes.get(2) + 1, spacerIndexes.get(3))));
			if(faultInt == 1) fault = Fault.MOTOR_FAULT;
			else if(faultInt == 2) fault = Fault.OPEN_DOOR_FAULT;
			else if(faultInt == 3) fault = Fault.CLOSE_DOOR_FAULT;
			else fault = Fault.NONE;
			
			noMoreRequests = Integer.parseInt(new String(Arrays.copyOfRange(array, spacerIndexes.get(3) + 1, spacerIndexes.get(4)))) == 1? true: false;
			
			time = new String(Arrays.copyOfRange(array, spacerIndexes.get(4) + 1, array.length));
		}
		else {
			//invalid byte array
			return;
		}
	}
	
	public void setNoMoreRequests() {
		noMoreRequests = true;
	}
	
	public boolean noMoreRequests() {
		return noMoreRequests;
	}

	/**
	 * getter function to get Time
	 * 
	 * @return string showing the time
	 */
	public String getTime() {
		return time;
	}
	
	public Fault getFault() {
		return fault;
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
	public static ElevatorDirection convertDirection(String dir) {
		ElevatorDirection direction;
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
	
	public static Fault convertFault(String f) {
		Fault fault;
		// if cases to cover all the cases
		if (f.equals("MOTOR")) {
			fault = Fault.MOTOR_FAULT;
		} else if (f.equals("OPEN_DOOR")) {
			fault = Fault.OPEN_DOOR_FAULT;
		} else if (f.equals("CLOSE_DOOR")) {
			fault = Fault.CLOSE_DOOR_FAULT;
		} else {
			fault = Fault.NONE;
		}
		return fault;
	}
	
	public static int faultToInt(Fault f) {
		if(f == Fault.MOTOR_FAULT) return 1;
		else if(f == Fault.OPEN_DOOR_FAULT) return 2;
		else if(f == Fault.CLOSE_DOOR_FAULT) return 3;
		else return 4;
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
		return time + " " + floor + " " + direction + " " + button + " " + fault + (noMoreRequests? " NO_MORE_REQUESTS": "");
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
			stream.write(String.valueOf(faultToInt(fault)).getBytes());
			stream.write(SPACER);
			stream.write(String.valueOf(noMoreRequests? 1: 0).getBytes());
			stream.write(SPACER);
			stream.write(time.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return stream.toByteArray();
	}

}
