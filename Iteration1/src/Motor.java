/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */

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
