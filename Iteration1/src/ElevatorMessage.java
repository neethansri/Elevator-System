import java.sql.Date;

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
	private String dir;
	private ElevatorDirection direction;

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
		this.dir = dir;
		this.direction = convertDirection(dir);

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
			direction = direction.UP;
		} else if (dir.equals("DOWN")) {
			direction = direction.DOWN;
		} else {
			direction = direction.STOPPED;
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

}
