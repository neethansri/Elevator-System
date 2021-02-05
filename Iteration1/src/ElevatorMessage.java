import java.util.Date;


public class ElevatorMessage {

	private String time;
	private int floor;
	private int button;
	private String dir;
	private ElevatorDirection direction;
	
	
	
	
	public ElevatorMessage(String time, int floor, String dir, int button) {
		this.time = time;
		this.floor = floor;
		this.button = button;
		this.dir = dir;		
		this.direction = convertDirection(dir);
		
	}
	
	public String getTime() {
		return time;
	}
	
	public int getFloor() {
		return floor;
	}
	
	public int getButton() {
		return button;
	}
	
	
	public ElevatorDirection convertDirection(String dir) {
		if(dir.equals("UP")) {	
		    direction = direction.UP;
		}
		else if(dir.equals("DOWN")){
			direction = direction.DOWN;
		}
		else {
			direction = direction.STOP;
		}
		return direction;
	}
	
	public ElevatorDirection getDirection() {
		return direction;
	}
	
	public String toString() {
		return time + " " + floor + " " + direction + " " + button;	
	}
	
	
}
