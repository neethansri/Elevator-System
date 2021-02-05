import java.io.BufferedReader;
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Scanner; // Import the Scanner class to read text files
import java.text.ParseException;
import java.text.SimpleDateFormat;


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
