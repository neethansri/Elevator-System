
public class Floor implements Runnable{
	
	public Floor() {
		
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
