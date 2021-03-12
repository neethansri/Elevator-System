import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
/**
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class Floor implements Runnable {

	// initializing variables
	Scheduler scheduler;
	private ElevatorMessage floorInfo;

	/**
	 * floor class constructor used to initialize floor
	 * 
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

    public void sendEvent(byte[] array) {
    	try {
			int numberOfSuccessfulPackets = 0;
			DatagramSocket socket = new DatagramSocket(22); //Creates a new socket. This will be used for sending and recieving packets
			//			socket.setSoTimeout(5000); //Sets the timeout value to 5 seconds. If 5 seconds elapses and no packet arrives on receive, an exception will be thrown
			InetAddress local = InetAddress.getLocalHost(); //Gets the local address of the computer 

				byte[] inputArray = array;

				DatagramPacket packetToSend = new DatagramPacket(inputArray, inputArray.length, local, 23); //Creates a packet from the dataArray, to be sent to the intermediate host
				DatagramPacket replyPacket = new DatagramPacket(new byte[4], 4); //Creates a packet to receive the acknowledgement in.

				socket.send(packetToSend); //Sends the packetToSend
				socket.receive(replyPacket); //Receive the ack from the intermediate host
				boolean receieved = false; //defines a flag to check for receieving a actual packet vs a nothing to report packet ("null")
				DatagramPacket receivedPacket = new DatagramPacket(new byte[4], 4); //Creates a new packet for receiving
				byte[] requestByteArray = "request".getBytes(); //Convert "request" into a byte array to send
				while (!receieved) { //Loop until a not null packet is recieved
					DatagramPacket requestPacket = new DatagramPacket(requestByteArray, requestByteArray.length, local, 24);
					socket.send(requestPacket); //Send a request to the intermediate server
					System.out.println("The request packet sent to the scheduler is: "+ requestPacket.getData());
					socket.receive(receivedPacket); //Recieve the response
					System.out.println("The packet received from the scheduler is: " + receivedPacket.getData());
					if (!(new String(receivedPacket.getData()).trim().equals("NA"))) {//If the response is not null, ie. a actual response
						numberOfSuccessfulPackets++;
						System.out.println("Client has received " + numberOfSuccessfulPackets + " packets so far");
						receieved = true; //Break out of loop
					}
					Thread.sleep(1000);
				}
			System.out.println("Client is finished. It has receievd " + numberOfSuccessfulPackets + " successful packets. (Should be 10)");
			socket.close(); //Close the socket
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
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
			   	byte[] inputArray = new byte[4];
				String[] data = Line.split(" "); // split each line by space and put it in a string array
				inputArray = data[0].getBytes();
				inputArray = data[1].getBytes();
				inputArray = data[2].getBytes();
				inputArray = data[3].getBytes();
				//floorInfo = new ElevatorMessage(data[0], Integer.parseInt(data[1]), data[2].toUpperCase(),
				//		Integer.parseInt(data[3])); // creates a ElevatorMessage class with the values from the file
				sendEvent(inputArray);

				//scheduler.sendEvent(floorInfo);// send this event from floor to elevator

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
