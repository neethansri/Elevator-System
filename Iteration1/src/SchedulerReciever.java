import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.time.LocalTime;
import java.util.Arrays;

/**
 * Thread in the Scheduler subsystem that recieves updates from the elevators and notifies the scheduler
 * @author Solan Siva 101067491
 * @author Ben Baggs 101122318
 * @author Vijay Ramalingom 101073072
 * @author Mohammad Issa 101065045
 * @author Neethan Sriranganathan 101082581
 */
public class SchedulerReciever implements Runnable{

	/**
	 * The scheduler associated with this receiver
	 */
	private Scheduler scheduler;
	
	private InetAddress local; 
	
	private DatagramSocket socket;
	
	private static final int SCHEDULER_PORT = 50;
	
	private static final int MESSAGE_SIZE_LIMIT = 100;
	
	private static final byte[] ACK_MESSAGE = "ack".getBytes();
	
	//if elevator sends first
	/*private static final byte[] ELEVATOR_MESSAGE_PROMPT = "prompt".getBytes();*/
	
	public static final byte ELEVATOR_UPDATE_SIGNATURE = 1;
	public static final byte ELEVATOR_MESSAGE_SIGNATURE = 2;
	
	private static final int FLOOR_PORT = 100;
	
	
	
	/**
	 * Constructor of class SchedulerReciever
	 * @param scheduler The scheduler associated with this reciever
	 */
	public SchedulerReciever(Scheduler scheduler) {
		this.scheduler = scheduler;
		try {
			socket = new DatagramSocket(SCHEDULER_PORT);
			local = InetAddress.getLocalHost();
		} catch (SocketException | UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	private synchronized void sendPacket(DatagramPacket packet) throws IOException {
		socket.send(packet);
	}
	
	//if scheduler sends first
	public void sendElevatorMessage(ElevatorMessage em, int elevatorPort) {
		byte message[] = em.toByteArray();
		
		DatagramPacket packetToSend = new DatagramPacket(message, message.length, local, elevatorPort);
		
		try {
			
			System.out.println("Time: " + LocalTime.now());
			System.out.println(Thread.currentThread().getName() + " sent " + new String(message) + " to Elevator " + elevatorPort + "\n");
			
			sendPacket(packetToSend);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void receiveMessages() {
		while(true) {
			DatagramPacket packetToReceive = new DatagramPacket(new byte[MESSAGE_SIZE_LIMIT], MESSAGE_SIZE_LIMIT);
			try {
				

				socket.receive(packetToReceive);
				byte data[] = Arrays.copyOf(packetToReceive.getData(), packetToReceive.getLength());
				
				System.out.println("Time: " + LocalTime.now());
				System.out.println(Thread.currentThread().getName() + " received " + new String(data) + "\n");
				
				//if elevator sends first
				/*if(Arrays.equals(data, ELEVATOR_MESSAGE_PROMPT)) {
					ElevatorMessage em = scheduler.getElevatorMessage(packetToReceive.getPort());
					if(em != null) {
						byte message[] = em.toByteArray();
						socket.send(new DatagramPacket(message, message.length, packetToReceive.getAddress(), packetToReceive.getPort()));
						continue;
					}
				}
				else {
					if(data[0] == ELEVATOR_UPDATE_SIGNATURE) {
						ElevatorUpdate eu = new ElevatorUpdate(data);
						scheduler.receiveElevatorUpdate(eu);
					}
					else if(data[0] == ELEVATOR_MESSAGE_SIGNATURE) {
						ElevatorMessage em = new ElevatorMessage(data);
						scheduler.receiveElevatorMessage(em);
					}
				}
				socket.send(new DatagramPacket(ACK_MESSAGE, ACK_MESSAGE.length, packetToReceive.getAddress(), packetToReceive.getPort()));*/
				
				if(Arrays.equals(data, ACK_MESSAGE)) continue;
				
				//if scheduler sends first
				if(data[0] == ELEVATOR_UPDATE_SIGNATURE) {
					ElevatorUpdate eu = new ElevatorUpdate(data);
					scheduler.receiveElevatorUpdate(eu, packetToReceive.getPort());
				}
				else if(data[0] == ELEVATOR_MESSAGE_SIGNATURE) {
					ElevatorMessage em = new ElevatorMessage(data);
					scheduler.receiveElevatorMessage(em);
				}
				
				System.out.println("Time: " + LocalTime.now());
				System.out.println(Thread.currentThread().getName() + " sent an acknowledgement back to " + (packetToReceive.getPort() == FLOOR_PORT? "floor": ("Elevator " + packetToReceive.getPort())) + ".\n");
				
				sendPacket(new DatagramPacket(ACK_MESSAGE, ACK_MESSAGE.length, packetToReceive.getAddress(), packetToReceive.getPort()));
				
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}
	}

	/**
	 * Continuously checks for updates from elevators and notifies the scheduler
	 */
	@Override
	public void run() {
		receiveMessages();
	}	
}
