import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Floor implements Runnable {
	
	
	Scheduler scheduler;
	private ElevatorMessage floorInfo;
	
	public Floor(Scheduler scheduler) {
		this.scheduler = scheduler;
			
	}

	@Override
	public void run() {
		  readFile();
    }	
	
	public void readFile() {
				try (BufferedReader read = new BufferedReader(new FileReader("./floorInfo.txt"))){
				
			        String Line;
			        Line = read.readLine();
			        while(Line != null) {
			        	String[] data = Line.split(" ");
			        	floorInfo = new ElevatorMessage(data[0], Integer.parseInt(data[1]), data[2].toUpperCase(), Integer.parseInt(data[3]));
			        
			        	scheduler.sendEvent(floorInfo);
			        	scheduler.receiveEvent();
			        	
			        	Line = read.readLine();
			        }
				}

				 catch (IOException e1) {
				e1.printStackTrace();
			}
}
}
