/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */

import static org.junit.Assert.*;
import org.junit.Test;
/*
 * This is a JUnit Test
 */
public class ElevatorTest {
	static Scheduler scheduler = new Scheduler();
	ElevatorMessage floorInfo = new ElevatorMessage("14:05:15.0", 2, "UP", 4);
	
	
	public static void main(String[] args) {
		Thread Floor = new Thread(new Floor(scheduler),"Floor");
		Thread Scheduler = new Thread(scheduler, "Scheduler");
		Thread Elevator = new Thread(new Elevator(scheduler),"Elevator");
		
		//starting the threads
		Floor.start();
		Elevator.start();
		Scheduler.start();
	}
/*
 * Test if the value read by the elevator is correct before sending to the scheduler
 * 
 */
	@Test
	public void floorTest() {
		scheduler.sendEvent(floorInfo);
		assertEquals("Floor SENT 14:05:15.0 2 UP 4", scheduler.getFloorTest());
	}
	
	@Test
	public void elevatorTest() {
		scheduler.handleFloorMessages();
		scheduler.getEvent();
		assertEquals("Elevator RECEIVED AND SENT BACK 14:05:15.0 2 UP 4", scheduler.getElevatorTest());
	}
	
	@Test
	public void schedulerTest() {
		scheduler.handleElevatorMessages();
		assertEquals("Scheduler PASSED 14:05:15.0 2 UP 4 FROM ELEVATOR TO FLOOR", scheduler.getSchedulerTest());
	}

}
