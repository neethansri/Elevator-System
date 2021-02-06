import static org.junit.Assert.*;

import org.junit.Test;
import java.util.concurrent.TimeUnit;
/*
 * This is a JUnit Test
 */
public class ElevatorTest {
/*
 * Test if the value read by the elevator is correct before sending to the scheduler
 * 
 */
	@Test
	public void Iteration1() {
		Scheduler scheduler = new Scheduler();
		
		Thread Floor = new Thread(new Floor(scheduler),"Floor");
		Thread Scheduler = new Thread(scheduler, "Scheduler");
		Thread Elevator = new Thread(new Elevator(scheduler),"Elevator");
	
		Floor.start();
		Elevator.start();
		Scheduler.start();
		try {
			TimeUnit.SECONDS.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		assertEquals("14:05:15.0 2 UP 4", scheduler.getTest());
	
	}

}
