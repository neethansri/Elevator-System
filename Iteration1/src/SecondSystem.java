import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import javax.swing.SwingConstants;


public class SecondSystem extends JFrame {

	public JPanel contentPane;
	
	public JLabel label2;

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
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(2, 1, 5, 5));
		
		JLabel label1 = new JLabel("Elevator "+e);
		label1.setHorizontalAlignment(SwingConstants.CENTER);
		
		label2 = new JLabel("Elevator "+e+" Status");
		label2.setHorizontalAlignment(SwingConstants.CENTER);
		
		contentPane.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		
		contentPane.add(label1);
		contentPane.add(label2);

		contentPane.repaint();
		contentPane.revalidate();
	}
	
	
}
