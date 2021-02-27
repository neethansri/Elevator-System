import java.time.LocalTime;
import java.util.*;

/*
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class Elevator implements Runnable {

	// initializing variables
	public static final int INITIAL_FLOOR = 1;
	
	private ElevatorState currentState;

	private List<ElevatorMessage> floorsToVisit;

	private Scheduler scheduler;
	private ElevatorDirection direction;
	private int floor;
	
	private int floorsTravelledWithoutStopping;
	
	private static final double ACCELERATION_RATE = 0.227964392;
	private static final double MAXIMUM_SPEED = 1.694915254;
	private static final double FLOOR_DISTANCE = 3.5;
	private static final double LOADING_TIME = 9391;
	
	private static double timeToAccelerateToMaxSpeed = MAXIMUM_SPEED / ACCELERATION_RATE;;
	private static double distanceToAccelerateToMaxSpeed = Math.pow(MAXIMUM_SPEED, 2) / (2 * ACCELERATION_RATE);

	/**
	 * Elevator constructor takes in scheduler object and initialized the elevator
	 * object
	 * 
	 * @param scheduler
	 */
	public Elevator(Scheduler scheduler) {
		this.scheduler = scheduler;
		direction = ElevatorDirection.STOPPED;
		floor = INITIAL_FLOOR;
		currentState = ElevatorState.STOPPED;
		floorsToVisit = new ArrayList<>();
		floorsTravelledWithoutStopping = 0;
	}
	
	private static int getTotalTime(int consecutiveFloors) {
		double totalDistance;
		if(consecutiveFloors * FLOOR_DISTANCE / 2 < distanceToAccelerateToMaxSpeed) {
			totalDistance = consecutiveFloors * FLOOR_DISTANCE / 2;
		}
		else totalDistance = consecutiveFloors * FLOOR_DISTANCE - distanceToAccelerateToMaxSpeed;

		double totalTime;
		
		if(totalDistance <= distanceToAccelerateToMaxSpeed) {
			totalTime = Math.sqrt(2 * totalDistance / ACCELERATION_RATE);
		}
		else {
			totalTime = timeToAccelerateToMaxSpeed + (totalDistance - distanceToAccelerateToMaxSpeed) / MAXIMUM_SPEED;
		}
		return (int) (1000 * totalTime);
	}
	
	public static int timeToNextApproachPoint(int consecutiveFloors) {
		if(consecutiveFloors <= 2) return getTotalTime(consecutiveFloors + 1);
		else return getTotalTime(consecutiveFloors + 1) - getTotalTime(consecutiveFloors);
	}
	
	public static int timeToDecelerate(int consecutiveFloors) {
		if((consecutiveFloors + 1) * FLOOR_DISTANCE / 2 < distanceToAccelerateToMaxSpeed) {
			return getTotalTime(consecutiveFloors + 1);
		}
		else return (int) (1000 * timeToAccelerateToMaxSpeed);
	}
	
	public synchronized void addFloorToVisit(ElevatorMessage floorNum) {
		if(!floorsToVisit.contains(floorNum)) floorsToVisit.add(floorNum);
	}
	
	private synchronized void removeFloorToVisit(int floorNum) {
		for(ElevatorMessage m: floorsToVisit) {
			if(m.getFloor() == floorNum) floorsToVisit.remove(m);
			break;
		}
	}
	
	private ElevatorMessage getOldestFloorToVisit() {
		/*ElevatorMessage oldestRequest = null;
		for(ElevatorMessage m: floorsToVisit) {
			if(oldestRequest == null) oldestRequest = m;
			else if(oldestRequest.getTime().after(m.getTime())) oldestRequest = m;
		}
		return oldestRequest;*/
		return floorsToVisit.get(0);
	}
	
	private boolean shouldStopAtFloor(int floorNum) {
		for(ElevatorMessage m: floorsToVisit) {
			if(m.getFloor() == floorNum) return true;
		}
		return false;
	}

	/**
	 * Run method in Elevator thread used to getEvent from scheduler thread
	 */
	@Override
	public void run() {
		
		while(true) {
			switch(currentState) {
				case STOPPED:				
					if(!floorsToVisit.isEmpty()) {
						currentState = ElevatorState.MOVING;
						if(getOldestFloorToVisit().getFloor() > floor) {
							direction = ElevatorDirection.UP;
							floor++;
						}
						else if(getOldestFloorToVisit().getFloor() < floor) {
							direction = ElevatorDirection.DOWN;
							floor--;
						}
						
						System.out.println("Time: " + LocalTime.now());
						System.out.println(Thread.currentThread().getName() + " is starting to move towards floor " + floor + "\n");
						
						//tell the scheduler that its moving
						scheduler.updateElevatorState(new ElevatorUpdate(floor, direction, LocalTime.now()));
						
						//sleep for the amount of time until the elevator approaches the next approach point
						try {
							Thread.sleep(timeToNextApproachPoint(floorsTravelledWithoutStopping));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					break;
			case MOVING:
				if(shouldStopAtFloor(floor)) {
					currentState = ElevatorState.STOPPED;
					direction = ElevatorDirection.STOPPED;
					floorsTravelledWithoutStopping = 0;
					removeFloorToVisit(floor);
					
					System.out.println("Time: " + LocalTime.now());
					System.out.println(Thread.currentThread().getName() + " is stopping at floor " + floor + "\n");
					
					//tell the scheduler that its stopping
					scheduler.updateElevatorState(new ElevatorUpdate(floor, direction, LocalTime.now()));
					
					//sleep for the amount of time until the elevator decelerates and stops at the floor
					try {
						Thread.sleep(timeToDecelerate(floorsTravelledWithoutStopping));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					
					System.out.println("Time: " + LocalTime.now());
					System.out.println(Thread.currentThread().getName() + " is skipping floor " + floor + "\n");
					
					floor += direction == ElevatorDirection.UP? 1: -1;
					floorsTravelledWithoutStopping++;
					
					//tell the scheduler that its continuing
					scheduler.updateElevatorState(new ElevatorUpdate(floor, direction, LocalTime.now()));
					
					//sleep for the amount of time until the elevator reaches the next approach point;
					try {
						Thread.sleep(timeToNextApproachPoint(floorsTravelledWithoutStopping));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				break;
			default:
				break;
			}
		}
	}

}
