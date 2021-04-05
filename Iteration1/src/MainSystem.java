import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class MainSystem extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	
	private static final int FLOOR_PORT = 100;

	private ArrayList<Integer> elevators = new ArrayList<>();
	
	private void startElevatorSystem() {
		chooseElevators();
		
		System.out.println("Time: " + LocalTime.now());
		System.out.println("Scheduler subsystem is starting!\n");
		// creating a scheduler object
		Thread schedulerThread = new Thread(new Scheduler(elevators, Integer.parseInt(textField_1.getText())), "Scheduler");
		schedulerThread.start();	

		System.out.println("Time: " + LocalTime.now());
		System.out.println("Elevator subsystem is starting!\n");

		for(Integer port: elevators) {
			Thread elevatorThread = new Thread(new Elevator(port), "Elevator " + port);
			elevatorThread.start();
		}
		
		System.out.println("Time: " + LocalTime.now());
		System.out.println("Floor subsystem is starting!\n");
		
		//creating a floor object
		Thread floorThread = new Thread(new Floor(FLOOR_PORT), "Floor");
		floorThread.start();
		
	}
	
	public String getnumOfElevators() {
		return textField.getText();
	}
	
	private void chooseElevators(){
		int numOfElevators = Integer.parseInt(textField.getText());
		for(int i = 1; i <= numOfElevators; i++) {
			elevators.add(Integer.valueOf(i));
		}
	}
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainSystem frame = new MainSystem();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainSystem() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 450);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		JLabel lblNewLabel = new JLabel("Concierge Desk");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 15));
		
		JLabel lblNewLabel_1 = new JLabel("Number of Elevators:");
		
		JLabel lblNewLabel_2 = new JLabel("Number of Floors:");
		
		textField = new JTextField();
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Start");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startElevatorSystem();
//				SecondSystem second = new SecondSystem();
//				second.setVisible(true);
			}

		});
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon("./mainsystem.png"));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(151)
					.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 365, Short.MAX_VALUE)
					.addGap(158))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(textField_1, 102, 102, 102)))
					.addGap(97)
					.addComponent(lblNewLabel_3)
					.addContainerGap(242, Short.MAX_VALUE))
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGap(168)
					.addComponent(btnNewButton)
					.addContainerGap(349, Short.MAX_VALUE))
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(lblNewLabel)
					.addGap(50)
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_1)
								.addComponent(textField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addGroup(gl_contentPane.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblNewLabel_2)
								.addComponent(textField_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
							.addGap(53))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addPreferredGap(ComponentPlacement.RELATED)))
					.addGap(21)
					.addComponent(btnNewButton)
					.addGap(120))
		);
		contentPane.setLayout(gl_contentPane);
	}
	
	
}
