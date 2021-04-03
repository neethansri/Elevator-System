/*
 * @author Solan Siva 101067491
 * @author Ben Bagg 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */

public class MotorTimer implements Runnable{
		
		private Elevator elevator;
		
		private int waitTime;
		
		private Thread elevatorThread;
		
		public MotorTimer(Elevator elevator, int waitTime) {
			this.elevator = elevator;
			this.waitTime = waitTime;
			this.elevatorThread = Thread.currentThread();
		}

		@Override
		public void run() {
			try {
				Thread.sleep(waitTime);
			} catch (InterruptedException e) {
				System.out.println("Motor Timer Interrupted");
			}
			elevator.setArrivalFlag();
			
			System.out.print("motor timer finished sleeping\n");
			
			elevatorThread.interrupt();
		}

	}

