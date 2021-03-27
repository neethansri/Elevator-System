
public class Motor{
	
	private Elevator elevator;
	
	public Motor(Elevator elevator) {
		this.elevator = elevator;
	}
	
	public void move(int waitTime) {
		Thread motorTimer = new Thread(new MotorTimer(elevator, waitTime), "Motor Timer");
		motorTimer.start();
	}
}
