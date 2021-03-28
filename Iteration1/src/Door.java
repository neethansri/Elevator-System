/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */

public class Door{

	private Elevator elevator;
	
	public Door(Elevator elevator) {
		this.elevator = elevator;
	}
	
	public void moveDoors() {
		Thread doorTimer = new Thread(new DoorTimer(elevator), "Door Timer");
		doorTimer.start();
	}

}
	
