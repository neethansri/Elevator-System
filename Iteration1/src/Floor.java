import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
/**
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class Floor implements Runnable {

	private ArrayList<ElevatorMessage> requests;
	
	private long earliestTime, currentTime;

	private FloorReceiver receiver;

	/**
	 * floor class constructor used to initialize floor
	 * 
	 * @param scheduler is of class Scheduler
	 */
	public Floor(int port) {
		
		requests = new ArrayList<>();

		receiver = new FloorReceiver(this, port);
		
		Thread receiverThread = new Thread(receiver, "Floor Receiver");
		receiverThread.start();
	}
	
	public FloorReceiver requestFloorReceiver() {
		return receiver;
	}
	
	/**
	 * Run method in Floor used to read the file for inputs
	 */
	@Override
	public void run() {
		readFile();
		System.out.println("Input file has been fully read.\n");
		generateRequests();
	}  	
	
	private void generateRequests() {
		
		LocalTime earliestLocalTime = null;
		for(ElevatorMessage em : requests) {
			LocalTime time = LocalTime.parse(em.getTime());
			if(earliestLocalTime == null) {
				earliestLocalTime = time;
			}
			else if(time.isBefore(earliestLocalTime)) {
				earliestLocalTime = time;
			}
		}
		
		this.earliestTime = earliestLocalTime.toNanoOfDay();
		
		ArrayList<Long> requestTimes = new ArrayList<>();
		
		for(int i = 0; i < requests.size(); i++) {
			requestTimes.add(LocalTime.parse(requests.get(i).getTime()).toNanoOfDay() - earliestTime);
		}
		
		currentTime = 0;
		
		while(!requests.isEmpty()) {
			
			for(int i = 0; i < requests.size(); i++) {
				if(currentTime >= requestTimes.get(i)) {
					receiver.sendElevatorMessage(requests.get(i));
					requests.remove(i);
					requestTimes.remove(i);
				}
			}
			currentTime += 2000000;
			
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
			}
		}
	}
    
	/**
	 * void function used to parse the file with the events
	 */
	public void readFile() {
		// try-catch for the parsing to catch IO errors
		try (BufferedReader read = new BufferedReader(new FileReader("./floorInfo.txt"))) {

			String Line;
			Line = read.readLine();
			while (Line != null) {
				String[] data = Line.split(" "); // split each line by space and put it in a string array
				
				requests.add(new ElevatorMessage(data[0], Integer.parseInt(data[1]), data[2].toUpperCase(),Integer.parseInt(data[3]), data[4].toUpperCase()));
				
				
				Line = read.readLine(); // if multiple lines, set the next line
			}
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
