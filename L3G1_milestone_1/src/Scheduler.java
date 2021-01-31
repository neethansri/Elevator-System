
public class Scheduler implements Runnable{

	public Scheduler(){
	
	}

	@Override
	public void run() {
		while(true) {
			
			try {
                Thread.sleep(500);
            } catch (InterruptedException e) {}
		}
		
	}

}
