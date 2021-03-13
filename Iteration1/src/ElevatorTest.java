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
	private ElevatorMessage floorInfo = new ElevatorMessage("14:05:15.0", 2, "UP", 4);
	static Scheduler scheduler = new Scheduler();
	static Floor floor = new Floor(100);
	static Elevator elevator = new Elevator(1);
	
	private FloorReceiver floorReceiver = floor.requestFloorReceiver();
	private SchedulerReceiver schedulerReceiver = scheduler.requestSchedulerReceiver();
	private ElevatorReceiver elevatorReceiver = elevator.requestElevatorReceiver();
	

	public static void main(String[] args) {

		Thread floorThread = new Thread(floor, "Floor");
		Thread schedulerThread = new Thread(scheduler, "SchedulerTest");
		Thread elevatorThread = new Thread(elevator, "Elevator " + 1);

		// starting the threads
		floorThread.start();
		elevatorThread.start();
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
		assertEquals("Floor sent 14:05:15.0 2 UP 4 to the scheduler", floorReceiver.getFloorTest1());

	}
	
	@Test
	public void schedulerTest1() {
		schedulerReceiver.receiveTest();
		assertEquals("Scheduler received 14:05:15.0 2 UP 4", schedulerReceiver.getSchedulerTest1());

	}
	@Test
	public void schedulerTest2() {
		schedulerReceiver.sendElevatorMessage(floorInfo, 1);
		assertEquals("Scheduler sent 14:05:15.0 2 UP 4 to Elevator 1", schedulerReceiver.getSchedulerTest2());

	}

//	@Test
//	public void elevatorTest() {
//		elevatorReceiver.receiveMessages();
//		assertEquals("Elevator 1 received 14:05:15.1 1 UP 7", elevatorReceiver.getElevatorTest());
//	}
	

}
