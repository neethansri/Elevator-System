
public class Scheduler implements Runnable {
	
	private ElevatorMessage  input = new ElevatorMessage();
	private ElevatorMessage  output = new ElevatorMessage();
	
	boolean flag = true; // if true receive from floor and if false send to floor

	@Override
	public void run() {
		while(true) {
			if(flag) {
			getEvent();//from floor
			//save what we get
			sendEvent();
			}
			else {
				
			}
		}
	
	}

	private void sendEvent() {
		// TODO Auto-generated method stub
		
	}

	private void getEvent() {
		// TODO Auto-generated method stub
		
	}

}
