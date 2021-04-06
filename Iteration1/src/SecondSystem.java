import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;



public class SecondSystem extends JFrame {

	public JPanel contentPane;
	
	public JLabel label2;

	public JLabel label3;
	
	public JLabel label4;
	
	public JLabel lblNewLabel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//SecondSystem frame = new SecondSystem();
					//frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	/**
	 * Create the frame.
	 */
	public SecondSystem(String e) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 453, 632);
		contentPane = new JPanel();
		setContentPane(contentPane);
		
		JLabel label1 = new JLabel(e);
		label1.setFont(new Font("Tahoma", Font.BOLD, 20));
		label1.setBounds(379, 128, 52, 28);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		contentPane.setLayout(null);
		
		label4 = new JLabel("");
		label4.setBounds(395, 409, 75, 39);
		contentPane.add(label4);
		
		label3 = new JLabel(e);
		label3.setFont(new Font("Tahoma", Font.BOLD, 18));
		label3.setForeground(Color.YELLOW);
		label3.setBackground(Color.WHITE);
		label3.setBounds(403, 379, 67, 28);
		contentPane.add(label3);
		
		label2 = new JLabel("Elevator "+e+" Status");
		label2.setForeground(Color.WHITE);
		label2.setBackground(Color.BLACK);
		label2.setBounds(0, 11, 441, 59);
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(label2);
		
		contentPane.add(label1);
		
		lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon("./elevatorIteration#5.jpg"));
		lblNewLabel.setBounds(0, 2, 470, 584);
		contentPane.add(lblNewLabel);

		contentPane.repaint();
		contentPane.revalidate();
	}
}
