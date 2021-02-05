import java.util.List;
import java.util.ArrayList;

public class Scheduler implements Runnable {
	private boolean flag = true;
	private ArrayList<ElevatorMessage> schedulerinfo = new ArrayList<ElevatorMessage>();
	private ArrayList<ElevatorMessage> previousrequest = new ArrayList<ElevatorMessage>();

	
	public synchronized void sendEvent(ElevatorMessage floorinfo) {
		while(schedulerinfo.isEmpty() != true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		schedulerinfo.add(floorinfo);
		notifyAll();
	
	}
 
	public synchronized void getEvent() {
		while(schedulerinfo.isEmpty() == true) {
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		previousrequest.addAll(schedulerinfo); 
		schedulerinfo.clear();
		System.out.println(previousrequest);
		previousrequest.clear();
		notifyAll();

	}
	
	public String sendConfirmation() {
		return "The elevator successfully stopped at" + requests() ;
	}
		
	public ArrayList<ElevatorMessage> requests(){
		return previousrequest;
	}
	
	public boolean getFlag() {
		return flag;
	}
 
	@Override
	public void run() {
		// TODO Auto-generated method stub
	}

}
