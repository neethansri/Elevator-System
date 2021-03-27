
public class DoorTimer implements Runnable{
	
	private Elevator elevator;
	
	private static final int DOOR_TIME = 2000;
	
	private Thread elevatorThread;
	
	public DoorTimer(Elevator elevator) {
		this.elevator = elevator;
		elevatorThread = Thread.currentThread();
	}

	@Override
	public void run() {
		try {
			Thread.sleep(DOOR_TIME);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		elevator.setState(elevator.getState() == ElevatorState.OPEN? ElevatorState.STOPPED: ElevatorState.OPEN);
		elevatorThread.interrupt();
	}

}
