import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * A thread in the Elevator subsystem that recieves messages from the scheduler and updates the elevator's list
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class FloorReciever implements Runnable{
	
	private static final int SCHEDULER_PORT = 50;
	
	private DatagramSocket socket;
	
	private InetAddress local; 
	
	private static final int MESSAGE_SIZE_LIMIT = 100;
	
	private static final byte[] ACK_MESSAGE = "ack".getBytes();
	
	//if floor sends first
	/*private static final byte[] ELEVATOR_UPDATE_PROMPT = "prompt".getBytes();
	private static final String SENDER = "Floor Update Prompt Thread";
	private static final String RECEIVER = "Floor Receiver Thread";*/

	private Floor floor;
	
	int floorPort;


	/**
	 * Constructor for class ElevatorReciever
	 * @param elevator
	 * @param scheduler
	 */
	public FloorReciever(Floor floor, int port) {
		this.floor = floor;
		floorPort = port;
		try {
			socket = new DatagramSocket(floorPort);
			local = InetAddress.getLocalHost();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} 
	}
	
	//if floor sends first
	/*private synchronized void promptForElevatorUpdates() {
		while(true) {
			byte message[] = ELEVATOR_UPDATE_PROMPT;
			
			DatagramPacket packetToSend = new DatagramPacket(message, message.length, local, SCHEDULER_PORT);
			
			try {
				socket.send(packetToSend);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	private synchronized void sendPacket(DatagramPacket packet) throws IOException {
		socket.send(packet);
	}
	
	public void sendElevatorMessage(ElevatorMessage em) {
		
		byte message[] = em.toByteArray();
		
		DatagramPacket packetToSend = new DatagramPacket(message, message.length, local, SCHEDULER_PORT);
		
		try {
			
			System.out.println("Time: " + LocalTime.now());
			System.out.println(Thread.currentThread().getName() + " sent " + new String(message) + " to scheduler\n");
			
			sendPacket(packetToSend);
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Continuously receives UDP messages and responds appropriately
	 */
	private void receiveMessages() {

			while(true) {
				//receives a UDP message
				DatagramPacket packetToReceive = new DatagramPacket(new byte[MESSAGE_SIZE_LIMIT], MESSAGE_SIZE_LIMIT);
				try {
					
					
					socket.receive(packetToReceive);
					
					
					byte data[] = Arrays.copyOf(packetToReceive.getData(), packetToReceive.getLength());
					
					System.out.println("Time: " + LocalTime.now());
					System.out.println(Thread.currentThread().getName() + " received " + new String(data) + "\n");
					
					if(!Arrays.equals(data, ACK_MESSAGE)) {
						//if the incoming message is an ElevatorMessage, adds the request to the elevator and sends back acknowledgement

						//process elevator update
						
						System.out.println("Time: " + LocalTime.now());
						System.out.println(Thread.currentThread().getName() + " sent an acknowledgement back to the scheduler.\n");
						
							sendPacket(new DatagramPacket(ACK_MESSAGE, ACK_MESSAGE.length, packetToReceive.getAddress(), packetToReceive.getPort()));
						
					}
					
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	}

	/**
	 * Continuously gets messages from the scheduler and notifies the elevator
	 */
	@Override
	public void run() {
		//if floor sends first
		/*if(Thread.currentThread().getName().equals(SENDER)) {
			promptForElevatorUpdates();
		}
		else {
			receiveMessages();
		}*/
		
		//if scheduler sends first
		receiveMessages();
	}
	
}
