import java.time.LocalTime;

/**
 * A message class used by the elevator subsystem to send updates about its location and direction to the scheduler
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class ElevatorUpdate {
	
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
	private LocalTime time;
	
	private int passengers;
	
	/**
	 * Constructor of class ElevatorUpdate
	 * @param floor The current floor of the elevator
	 * @param direction The current direction of the elevator
	 * @param time The time that the elevator sent the update
	 */
	public ElevatorUpdate(int floor, ElevatorDirection direction, int passengers, LocalTime time) {
		this.floor = floor;
		this.direction = direction;
		this.time = time;
		this.passengers = passengers;
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
	public LocalTime getTime() {
		return time;
	}
	
	/**
	 * formats the contents of the message as a string
	 */
	public String toString() {
		return floor + direction.toString() + time;
	}
}
