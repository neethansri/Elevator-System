/**
 * 
 * @author Solan Siva
 * @author Ben Bagg
 * @author Vijay Ramalingom
 * @author Mohammad Issa
 * @author Neethan Sriranganathan 101082581
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


public class Floor implements Runnable {
	
	//initializing variables
	Scheduler scheduler;
	private ElevatorMessage floorInfo;
	
	/**
	 * floor class constructor used to initialize floor
	 * @param scheduler is of class Scheduler
	 */
	public Floor(Scheduler scheduler) {
		this.scheduler = scheduler;
			
	}
	/**
	 * Run method in Floor used to read the file for inputs
	 */
	@Override
	public void run() {
		  readFile();
    }	
	
	/**
	 * void function used to parse the file with the events
	 */
	public void readFile() {
		//try-catch for the parsing to catch IO errors
				try (BufferedReader read = new BufferedReader(new FileReader("./floorInfo.txt"))){
				
			        String Line;
			        Line = read.readLine();
			        while(Line != null) {

			        	String[] data = Line.split(" "); // split each line by space and put it in a string array
			        	floorInfo = new ElevatorMessage(data[0], Integer.parseInt(data[1]), data[2].toUpperCase(), Integer.parseInt(data[3])); // creates a ElevatorMessage class with the values from the file
			        
			        	scheduler.sendEvent(floorInfo);//send this event from floor to elevator
			        	scheduler.receiveEvent();//receive event from elevator back to floor

			        	
			        	Line = read.readLine(); //if multiple lines, set the next line
			        }
				}

				 catch (IOException e1) {
				e1.printStackTrace();
			}
}
}
