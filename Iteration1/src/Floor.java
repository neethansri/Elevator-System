import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.time.LocalTime;
/**
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class Floor implements Runnable {

	private FloorReciever receiver;

	/**
	 * floor class constructor used to initialize floor
	 * 
	 * @param scheduler is of class Scheduler
	 */
	public Floor(int port) {

		receiver = new FloorReciever(this, port);
		
		Thread receiverThread = new Thread(receiver, "Floor Receiver");
		receiverThread.start();
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
		// try-catch for the parsing to catch IO errors
		try (BufferedReader read = new BufferedReader(new FileReader("./floorInfo.txt"))) {

			String Line;
			Line = read.readLine();
			while (Line != null) {
				String[] data = Line.split(" "); // split each line by space and put it in a string array
				
				receiver.sendElevatorMessage(new ElevatorMessage(data[0], Integer.parseInt(data[1]), data[2].toUpperCase(),Integer.parseInt(data[3])));

				Line = read.readLine(); // if multiple lines, set the next line

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
				}
			}
		}

		catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
