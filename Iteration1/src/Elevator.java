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
	/**
	 * The floor that the elevator will initially start at
	 */
	public static final int INITIAL_FLOOR = 1;
	/**
	 * The current state of the elevator
	 */
	private ElevatorState currentState;
	/**
	 * A list of pending requests from the scheduler
	 */
	private List<ElevatorMessage> floorsToVisit;
	/**
	 * The scheduler giving this elevator requests
	 */
	private Scheduler scheduler;
	/**
	 * The direction of the elevator
	 */
	private ElevatorDirection direction;
	/**
	 * The floor number of the floor that the elevator is currently at.
	 * If the elevator is between floors, the value is the next floor that the elevator will arrive at
	 */
	private int floor;
	/**
	 * The number of floors that the elevator has travelled without stopping at a floor.
	 * This is useful when calculating the elevator's speed and acceleration
	 */
	private int floorsTravelledWithoutStopping;
	/**
	 * The acceleration rate of the elevator in m/s^2.
	 * Calculated in Iteration 0
	 */
	private static final double ACCELERATION_RATE = 0.227964392;
	/**
	 * The maximum speed of the elevator in m/s.
	 * Calculated in Iteration 0
	 */
	private static final double MAXIMUM_SPEED = 1.694915254;
	/**
	 * The distance between floors in m.
	 * Calculated in Iteration 0
	 */
	private static final double FLOOR_DISTANCE = 3.5;
	/**
	 * The time that the elevator will open its doors for loading/unloading at a floor.
	 * Calculated in Iteration 0
	 */
	private static final double LOADING_TIME = 9391;
	/**
	 * The time that the elevator will take to accelerate to maximum speed from a full stop
	 * It is a static field because it is used in multiple static methods
	 */
	private static final double TIME_TO_ACCELERATE_TO_MAX_SPEED = MAXIMUM_SPEED / ACCELERATION_RATE;
	/**
	 * The distance that the elevator will cover while accelerating to maximum speed from a full stop
	 * It is a static field because it is used in multiple static methods
	 */
	private static final double DISTANCE_TO_ACCELERATE_TO_MAX_SPEED = Math.pow(MAXIMUM_SPEED, 2) / (2 * ACCELERATION_RATE);
	/**
	 * Elevator constructor takes in scheduler object and initializes the elevator
	 * object
	 * 
	 * @param scheduler The scheduler responsible for giving this elevator requests
	 */
	public Elevator(Scheduler scheduler) {
		this.scheduler = scheduler;
		direction = ElevatorDirection.STOPPED;
		floor = INITIAL_FLOOR;
		currentState = ElevatorState.STOPPED;
		floorsToVisit = new ArrayList<>();
		floorsTravelledWithoutStopping = 0;
	}
	
	/**
	 * Calculates the time required for the elevator to move to the approach point for the floor a specified number of floors away from a full stop
	 * @param consecutiveFloors The number of floors away the elevator needs to go 
	 * (A value of 5 means that the method returns the amount of time needed for the elevator 
	 * to go from stopped at floor 1 to the point where it must decide whether or 
	 * not to decelerate to stop at floor 5)
	 * @return The required length of time
	 */
	private static int getTotalTime(int consecutiveFloors) {
		double totalDistance;
		if(consecutiveFloors * FLOOR_DISTANCE / 2 < DISTANCE_TO_ACCELERATE_TO_MAX_SPEED) {
			totalDistance = consecutiveFloors * FLOOR_DISTANCE / 2;
		}
		else totalDistance = consecutiveFloors * FLOOR_DISTANCE - DISTANCE_TO_ACCELERATE_TO_MAX_SPEED;

		double totalTime;
		
		if(totalDistance <= DISTANCE_TO_ACCELERATE_TO_MAX_SPEED) {
			totalTime = Math.sqrt(2 * totalDistance / ACCELERATION_RATE);
		}
		else {
			totalTime = TIME_TO_ACCELERATE_TO_MAX_SPEED + (totalDistance - DISTANCE_TO_ACCELERATE_TO_MAX_SPEED) / MAXIMUM_SPEED;
		}
		return (int) (1000 * totalTime);
	}
	
	/**
	 * Calculates the time that the elevator will take until it gets to the approach point of the next floor.
	 * Depends on its current speed and acceleration.
	 * @param consecutiveFloors The number of floors that the elevator has moved past without stopping
	 * @return The required length of time
	 */
	private static int timeToNextApproachPoint(int consecutiveFloors) {
		if(consecutiveFloors <= 2) return getTotalTime(consecutiveFloors + 1);
		else return getTotalTime(consecutiveFloors + 1) - getTotalTime(consecutiveFloors);
	}
	
	/**
	 * Calculates the time that the elevator will take to decelerate and stop at the next floor.
	 * Depends on its current speed and acceleration.
	 * @param consecutiveFloors The number of floors that the elevator has moved past without stopping
	 * @return The required length of time
	 */
	private static int timeToDecelerate(int consecutiveFloors) {
		if((consecutiveFloors + 1) * FLOOR_DISTANCE / 2 < DISTANCE_TO_ACCELERATE_TO_MAX_SPEED) {
			return getTotalTime(consecutiveFloors + 1);
		}
		else return (int) (1000 * TIME_TO_ACCELERATE_TO_MAX_SPEED);
	}
	
	/**
	 * Adds a request to the elevator's list
	 * @param floorNum The ElevatorMessage to be added
	 */
	public synchronized void addFloorToVisit(ElevatorMessage message) {
		if(!floorsToVisit.contains(message)) floorsToVisit.add(message);
	}
	
	/**
	 * Removes a request from the elevator's list
	 * @param floorNum The floor number of the request to be removed
	 */
	private synchronized void removeFloorToVisit(int floorNum) {
		for(ElevatorMessage m: floorsToVisit) {
			if(m.getFloor() == floorNum) floorsToVisit.remove(m);
			break;
		}
	}
	
	/**
	 * Determines whether or not the elevator should stop at an approaching floor
	 * @param floorNum The next floor being approached
	 * @return True if the elevator should stop, false otherwise
	 */
	private boolean shouldStopAtFloor(int floorNum) {
		for(ElevatorMessage m: floorsToVisit) {
			if(m.getFloor() == floorNum) return true;
		}
		return false;
	}

	/**
	 * Run method in Elevator simulating the state machine
	 */
	@Override
	public void run() {
		
		while(true) {
			switch(currentState) {
				case STOPPED:	
					//the elevator is stopped, and therefore ready to move from a floor
					if(!floorsToVisit.isEmpty()) {
						//the elevator has pending requests and shall start moving
						currentState = ElevatorState.MOVING;
						if(floorsToVisit.get(0).getFloor() > floor) {
							direction = ElevatorDirection.UP;
							floor++;
						}
						else if(floorsToVisit.get(0).getFloor() < floor) {
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
				//the elevator is moving, and therefore at the approach point of a floor
				if(shouldStopAtFloor(floor)) {
					//the elevator should stop at the next floor
					currentState = ElevatorState.STOPPED;
					direction = ElevatorDirection.STOPPED;
					floorsTravelledWithoutStopping = 0;
					removeFloorToVisit(floor);
					
					System.out.println("Time: " + LocalTime.now());
					System.out.println(Thread.currentThread().getName() + " is stopping at floor " + floor + "\n");
					
					//tell the scheduler that its stopping
					scheduler.updateElevatorState(new ElevatorUpdate(floor, direction, LocalTime.now()));
					
					//sleep for the amount of time until the elevator decelerates and stops at the floor + the loading time while stopped
					try {
						Thread.sleep((long) (timeToDecelerate(floorsTravelledWithoutStopping) + LOADING_TIME));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				else {
					//the elevator should skip the next floor and move to the next approach point
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
