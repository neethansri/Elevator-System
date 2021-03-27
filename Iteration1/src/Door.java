
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
	
