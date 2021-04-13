/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

/*
 * This is a JUnit Test
 */
public class ElevatorTest {
	private ElevatorMessage floorInfo = new ElevatorMessage("14:05:15.0", 2, "UP", 4, "NO_FAULT",true);

	private static ArrayList<Integer> lists = new ArrayList<Integer>();
	static {
		lists.add(1);
		lists.add(2);
		lists.add(3);
	}
	
	static Scheduler scheduler = new Scheduler(lists,7);
	private static ThirdSystem GUI2 = new ThirdSystem("Floor ");
	static Floor floor = new Floor(100, GUI2);

	static Elevator elevator = new Elevator(1,GUI2);
	
	private FloorReceiver floorReceiver = floor.requestFloorReceiver();

	private SchedulerReceiver schedulerReceiver = scheduler.requestSchedulerReceiver();
	
	private static Thread floorThread = new Thread(floor, "Floor");

	private static Thread schedulerThread = new Thread(scheduler, "SchedulerTest");

	
	
	public static void main(String[] args) {

	
		// starting the threads
		floorThread.start();
		schedulerThread.start();

	}

	/*
	 * Test the message sent from the floor to the scheduler
	 * 
	 * 
	 */
	@Test
	public void floorTest() {
		floorReceiver.sendElevatorMessage(floorInfo);
		assertEquals("Floor sent 14:05:15.0 2 UP 4 NONE NO_MORE_REQUESTS to the scheduler", floorReceiver.getFloorTest1());

	}
	
	@Test
	public void schedulerTest1() {
		assertEquals("14:05:15.0 2 UP 4 NONE NO_MORE_REQUESTS", scheduler.getElevatorMessage());
	}
	
	
	@Test
	public void schedulerTest2() {
		schedulerReceiver.sendElevatorMessage(floorInfo, 1);
		assertEquals("Scheduler sent 14:05:15.0 2 UP 4 NONE NO_MORE_REQUESTS to Elevator 1", schedulerReceiver.getSchedulerTest2());
	}
	
	@Test
	public void elevatorTest() throws InterruptedException {
		elevator.addRequest(floorInfo);
		assertEquals(2, elevator.getMessage());
	}
	


}
