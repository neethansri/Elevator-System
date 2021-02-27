import java.time.LocalTime;

public class ElevatorUpdate {
	private int floor;
	private ElevatorDirection direction;
	private LocalTime time;
	
	public ElevatorUpdate(int floor, ElevatorDirection direction, LocalTime time) {
		this.floor = floor;
		this.direction = direction;
		this.time = time;
	}

	public int getFloor() {
		return floor;
	}

	public ElevatorDirection getDirection() {
		return direction;
	}

	public LocalTime getTime() {
		return time;
	}
	
	public String toString() {
		return floor + direction.toString() + time;
	}
}
