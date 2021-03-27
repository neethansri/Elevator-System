
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
				e.printStackTrace();
			}
			elevator.setArrivalFlag();
			
			System.out.print("motor timer finished sleeping\n");
			
			elevatorThread.interrupt();
		}

	}

