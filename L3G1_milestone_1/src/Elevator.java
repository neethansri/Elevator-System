
public class Elevator implements Runnable{

	public Elevator() {
		
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
