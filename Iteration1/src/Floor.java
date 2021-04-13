import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalTime;
import java.util.ArrayList;
import javax.swing.ImageIcon;
/**
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class Floor implements Runnable {

	private ThirdSystem GUI2;
	
	private long earliestTime, currentTime;

	private FloorReceiver receiver;

	/**
	 * floor class constructor used to initialize floor
	 * 
	 * @param scheduler is of class Scheduler
	 */
	public Floor(int port, ThirdSystem GUILamp) {
		
		GUI2 = GUILamp;
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
    
	public void floorLamp(int data, String dir) {
		switch(data) {
			case 1:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_45.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_23.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 2:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_46.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_25.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 3:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_47.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_26.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 4:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_48.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_27.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 5:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_49.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_28.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 6:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_50.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_29.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 7:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_51.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_30.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 8:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_52.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_31.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 9:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_53.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_32.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 10:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_54.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_33.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 11:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_55.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_34.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 12:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_56.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_24.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 13:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_57.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_35.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 14:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_58.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_36.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 15:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_59.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_37.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 16:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_60.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_38.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 17:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_61.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_39.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 18:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_62.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_40.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 19:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_63.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_41.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 20:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_64.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_42.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 21:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_65.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_43.setIcon(new ImageIcon("./down.png"));
				}
				break;
			case 22:
				if(dir.equals("UP")) {
					GUI2.lblNewLabel_66.setIcon(new ImageIcon("./greenup.png"));
				}
				else if(dir.equals("DOWN")) {
					GUI2.lblNewLabel_44.setIcon(new ImageIcon("./down.png"));
				}
				break;
				
		
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
				receiver.sendElevatorMessage(new ElevatorMessage(data[0], Integer.parseInt(data[1]), data[2].toUpperCase(),Integer.parseInt(data[3]), data[4].toUpperCase()));
				floorLamp(Integer.parseInt(data[1]), data[2].toUpperCase());
				Line = read.readLine(); // if multiple lines, set the next line
			}
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
