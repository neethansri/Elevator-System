import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Floor implements Runnable {
	
	
	Scheduler scheduler;
	private ElevatorMessage floorinfo;
	
	public Floor(Scheduler scheduler) {
		this.scheduler = scheduler;
			
	}

	@Override
	public void run() {
		while(true) {
		    scheduler.sendEvent(readFile());
	    }
    }	
	
	public ElevatorMessage readFile() {
				try (BufferedReader read = new BufferedReader(new FileReader("./floorInfo.txt"))){
				
			        String Line;
			        Line = read.readLine();
			        while(Line != null) {
			        	String[] data = Line.split(" ");
			        	floorinfo = new ElevatorMessage(data[0], Integer.parseInt(data[1]), data[2].toUpperCase(), Integer.parseInt(data[3]));
			        }
				}

				 catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	

		
		
	
	return floorinfo;

}
}
