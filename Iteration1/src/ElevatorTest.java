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
	private ElevatorMessage floorInfo = new ElevatorMessage("14:05:15.0", 2, "UP", 4, "NO_FAULT");

	private static final ArrayList<Integer> lists = new ArrayList<>(){{
		lists.add(1);
		lists.add(2);
		lists.add(3);
	}};
	static Scheduler scheduler = new Scheduler(lists,7);

	static Floor floor = new Floor(100);

	static Elevator elevator = new Elevator(1);
	
	private FloorReceiver floorReceiver = floor.requestFloorReceiver();

	private SchedulerReceiver schedulerReceiver = scheduler.requestSchedulerReceiver();
	
	private static Thread floorThread;

	private static Thread schedulerThread;

	
	
	public static void main(String[] args) {

		floorThread = new Thread(floor, "Floor");
		schedulerThread = new Thread(scheduler, "SchedulerTest");


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
		assertEquals("Floor sent 14:05:15.0 2 UP 4 NONE to the scheduler", floorReceiver.getFloorTest1());

	}
	
	@Test
	public void schedulerTest1() {
		assertEquals("14:05:15.0 2 UP 4 NONE", scheduler.getElevatorMessage());
	}
	
	
	@Test
	public void schedulerTest2() {
		schedulerReceiver.sendElevatorMessage(floorInfo, 1);
		assertEquals("Scheduler sent 14:05:15.0 2 UP 4 NONE to Elevator 1", schedulerReceiver.getSchedulerTest2());
	}
	
	@Test
	public void elevatorTest() throws InterruptedException {
		elevator.addRequest(floorInfo);
		assertEquals(2, elevator.getMessage());
	}
	


}
