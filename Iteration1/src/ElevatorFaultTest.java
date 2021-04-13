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


public class ElevatorFaultTest {
	
	private ElevatorMessage floorInfo2 = new ElevatorMessage("14:05:15.0", 4, "DOWN", 2, "MOTOR", false);
	private static final ArrayList<Integer> lists = new ArrayList<Integer>();
	static {
		lists.add(1);
		lists.add(2);
		lists.add(3);
		
	}
		
	
	private static ThirdSystem GUI2 = new ThirdSystem("Floor ");
	static Scheduler scheduler2 = new Scheduler(lists,7);
	static Floor floor2 = new Floor(100, GUI2);
	private FloorReceiver floorReceiver2 = floor2.requestFloorReceiver();
	
	private static Thread floorThread2;
	private static Thread schedulerThread2;
	
	
	public static void main(String[] args) {
		floorThread2 = new Thread(floor2, "Floor2");
		schedulerThread2 = new Thread(scheduler2, "SchedulerTest2");
		
		floorThread2.start();
		schedulerThread2.start();

	}
	
	@Test
	public void floorTest() {
		floorReceiver2.sendElevatorMessage(floorInfo2);
		assertEquals("Floor sent 14:05:15.0 4 DOWN 2 MOTOR_FAULT to the scheduler", floorReceiver2.getFloorTest1());
	}
	
	@Test
	public void schedulerTest1() {
		assertEquals("14:05:15.0 4 DOWN 2 MOTOR_FAULT", scheduler2.getElevatorMessage());
	}
	
}
