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
public class ElevatorReciever implements Runnable{
	
	private static final int SCHEDULER_PORT = 50;
	
	private int elevatorPort;
	
	private DatagramSocket socket, sendSocket;
	
	private InetAddress local; 
	
	private static final int MESSAGE_SIZE_LIMIT = 100;
	
	private static final byte[] ACK_MESSAGE = "ack".getBytes();
	
	//if elevator sends first
	/*private static final byte[] ELEVATOR_MESSAGE_PROMPT = "prompt".getBytes();
	private static final String SENDER = "Elevator Request Prompt Thread";
	private static final String RECEIVER = "Elevator Receiver Thread";*/
	
	/**
	 * The elevator associated with this reciever
	 */
	private Elevator elevator;


	/**
	 * Constructor for class ElevatorReciever
	 * @param elevator
	 * @param scheduler
	 */
	public ElevatorReciever(Elevator elevator, int port) {
		this.elevator = elevator;
		elevatorPort = port;
		try {
			socket = new DatagramSocket(elevatorPort);
			sendSocket = new DatagramSocket();
			local = InetAddress.getLocalHost();
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e1) {
			e1.printStackTrace();
		} 
	}
	
	//if elevator sends first
	/*private synchronized void promptForElevatorMessages() {
		while(true) {
			byte message[] = ELEVATOR_MESSAGE_PROMPT;
			
			DatagramPacket packetToSend = new DatagramPacket(message, message.length, local, SCHEDULER_PORT);
			
			try {
				socket.send(packetToSend);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}*/
	
	public void sendElevatorUpdate(ElevatorUpdate eu) {
		byte message[] = eu.toByteArray();
		DatagramPacket packetToSend = new DatagramPacket(message, message.length, local, SCHEDULER_PORT);
		try {
			
			System.out.println("Time: " + LocalTime.now());
			System.out.println(Thread.currentThread().getName() + " sent " + new String(message) + " to scheduler.\n");
			
			socket.send(packetToSend);
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
						elevator.addRequest(new ElevatorMessage(data));
						
						System.out.println("Time: " + LocalTime.now());
						System.out.println(Thread.currentThread().getName() + " sent an acknowledgement back to the scheduler.\n");
						
						socket.send(new DatagramPacket(ACK_MESSAGE, ACK_MESSAGE.length, packetToReceive.getAddress(), packetToReceive.getPort()));	
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
		//if elevator sends first
		/*if(Thread.currentThread().getName().equals(SENDER)) {
			promptForElevatorMessages();
		}
		else {
			receiveMessages();
		}*/
		
		//if scheduler sends first
		receiveMessages();
	}
	
}
