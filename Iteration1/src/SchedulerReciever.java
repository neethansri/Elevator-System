import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

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
	 * The scheduler associated with this reciever
	 */
	private Scheduler scheduler;
	
	/**
	 * Constructor of class SchedulerReciever
	 * @param scheduler The scheduler associated with this reciever
	 */
	public SchedulerReciever(Scheduler scheduler) {
		this.scheduler = scheduler;
	}

	public void replyMessage() {
		try {
			DatagramSocket receiveSocket = new DatagramSocket(25); //creates a socket bound to port portNumber
			System.out.println(Thread.currentThread().getName() + " is running on port: " + 25);
			//local = InetAddress.getLocalHost(); //Creates inetaddress containing localhost
			byte[] ackData = "ack".getBytes(); //Defines ack byte array
			byte[] negAck = "NA".getBytes();;

			while (true) { //loop infinitely  
				DatagramPacket recievedPacket = new DatagramPacket(new byte[4], 4);
				receiveSocket.receive(recievedPacket);//Recieve a packet
				byte[] data = recievedPacket.getData();
				
//				printPacket(recievedPacket, false);
				ElevatorMessage floorInfo = new ElevatorMessage(data[0], Integer.parseInt(data[1]), data[2].toUpperCase(),
						Integer.parseInt(data[3])); // creates a ElevatorMessage class with the values from the file
				DatagramPacket ackPacket;
				if (new String(recievedPacket.getData()).trim().equals("request")) { //If the recievedPacket was a request
					if (scheduler.getfromFloor().isEmpty()) { //If there are no packets to forward
						ackPacket = new DatagramPacket(negAck, negAck.length, InetAddress.getLocalHost(), recievedPacket.getPort()); //acknowledge that packet
//						printPacket(ackPacket, true);
						receiveSocket.send(ackPacket);//acknowledge that packet						
					} else {
						System.out.println(Thread.currentThread().getName()+": Request Receieved, there are " + pendingElevatorUpdates.size()+ "messages waiting");
//						printPacket(queue.peek(), true);
						receiveSocket.send(scheduler.getfromFloor()); //Send the first packet waiting
						
					}
				} else { //if the recievedPacket was not a request, it must have been data
					ackPacket = new DatagramPacket(ackData, ackData.length, InetAddress.getLocalHost(), recievedPacket.getPort()); //acknowledge that packet
//					printPacket(ackPacket, true);
					receiveSocket.send(ackPacket);//acknowledge that packet

					if (recievedPacket.getPort() == 69) { //If the data came from the server, it must be going to client
						recievedPacket.setPort(22); //Set the packet's port to the client port
					} else if(recievedPacket.getPort()==22){ //The data must have come from the client, so it is going to the server
						recievedPacket.setPort(69); //Set the packets port to the server port
					}
					pendingElevatorUpdates.add(recievedPacket); //Enqueue the packet
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	/**
	 * Continuously checks for updates from elevators and notifies the scheduler
	 */
	@Override
	public void run() {
		while(true) {
		scheduler.readElevatorState();
		}
	}	
}
