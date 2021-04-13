import javax.swing.JFrame;
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
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.List;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.TextField;
import java.awt.Label;

public class ThirdSystem extends JFrame{
	
	public JLabel lblNewLabel_23;
	
	public JLabel lblNewLabel_24;
	
	public JLabel lblNewLabel_25;
	
	public JLabel lblNewLabel_35;
	
	public JLabel lblNewLabel_26;
	
	public JLabel lblNewLabel_36;
	
	public JLabel lblNewLabel_27;
	
	public JLabel lblNewLabel_37;
	
	public JLabel lblNewLabel_28;
	
	public JLabel lblNewLabel_38;
	
	public JLabel lblNewLabel_29;
	
	public JLabel lblNewLabel_39;
	
	public JLabel lblNewLabel_30;
	
	public JLabel lblNewLabel_40;
	
	public JLabel lblNewLabel_31;
	
	public JLabel lblNewLabel_41;
	
	public JLabel lblNewLabel_32;
	
	public JLabel lblNewLabel_42;
	
	public JLabel lblNewLabel_33;
	
	public JLabel lblNewLabel_43;
	
	public JLabel lblNewLabel_34;
	
	public JLabel lblNewLabel_44;
	
	public JLabel lblNewLabel_45;
	
	public JLabel lblNewLabel_56;
	
	public JLabel lblNewLabel_46;
	
	public JLabel lblNewLabel_57;
	
	public JLabel lblNewLabel_47;
	
	public JLabel lblNewLabel_58;
	
	public JLabel lblNewLabel_48;
	
	public JLabel lblNewLabel_59;
	
	public JLabel lblNewLabel_49;
	
	public JLabel lblNewLabel_60;
	
	public JLabel lblNewLabel_50;
	
	public JLabel lblNewLabel_61;
	
	public JLabel lblNewLabel_51;
	
	public JLabel lblNewLabel_62;
	
	public JLabel lblNewLabel_52;
	
	public JLabel lblNewLabel_63;
	
	public JLabel lblNewLabel_53;
	
	public JLabel lblNewLabel_64;
	
	public JLabel lblNewLabel_54;
	
	public JLabel lblNewLabel_65;
	
	public JLabel lblNewLabel_55;
	
	public JLabel lblNewLabel_66;
	
	
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
	
	public ThirdSystem(String e) {
		setBounds(100, 100, 286, 320);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {46, 0, 0, 0, 46, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
		
		JLabel lblNewLabel_1 = new JLabel("Floors");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 0;
		getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JLabel lblNewLabel = new JLabel("1");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		lblNewLabel_23 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_23 = new GridBagConstraints();
		gbc_lblNewLabel_23.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_23.gridx = 2;
		gbc_lblNewLabel_23.gridy = 1;
		getContentPane().add(lblNewLabel_23, gbc_lblNewLabel_23);
		
		lblNewLabel_45 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_45 = new GridBagConstraints();
		gbc_lblNewLabel_45.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_45.gridx = 3;
		gbc_lblNewLabel_45.gridy = 1;
		getContentPane().add(lblNewLabel_45, gbc_lblNewLabel_45);
		
		JLabel lblNewLabel_12 = new JLabel("12");
		GridBagConstraints gbc_lblNewLabel_12 = new GridBagConstraints();
		gbc_lblNewLabel_12.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_12.gridx = 4;
		gbc_lblNewLabel_12.gridy = 1;
		getContentPane().add(lblNewLabel_12, gbc_lblNewLabel_12);
		
		lblNewLabel_24 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_24 = new GridBagConstraints();
		gbc_lblNewLabel_24.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_24.gridx = 5;
		gbc_lblNewLabel_24.gridy = 1;
		getContentPane().add(lblNewLabel_24, gbc_lblNewLabel_24);
		
		lblNewLabel_56 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_56 = new GridBagConstraints();
		gbc_lblNewLabel_56.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_56.gridx = 7;
		gbc_lblNewLabel_56.gridy = 1;
		getContentPane().add(lblNewLabel_56, gbc_lblNewLabel_56);
		
		JLabel lblNewLabel_2 = new JLabel("2");
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 2;
		getContentPane().add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		lblNewLabel_25 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_25 = new GridBagConstraints();
		gbc_lblNewLabel_25.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_25.gridx = 2;
		gbc_lblNewLabel_25.gridy = 2;
		getContentPane().add(lblNewLabel_25, gbc_lblNewLabel_25);
		
		lblNewLabel_46 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_46 = new GridBagConstraints();
		gbc_lblNewLabel_46.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_46.gridx = 3;
		gbc_lblNewLabel_46.gridy = 2;
		getContentPane().add(lblNewLabel_46, gbc_lblNewLabel_46);
		
		JLabel lblNewLabel_13 = new JLabel("13");
		GridBagConstraints gbc_lblNewLabel_13 = new GridBagConstraints();
		gbc_lblNewLabel_13.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_13.gridx = 4;
		gbc_lblNewLabel_13.gridy = 2;
		getContentPane().add(lblNewLabel_13, gbc_lblNewLabel_13);
		
		lblNewLabel_35 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_35 = new GridBagConstraints();
		gbc_lblNewLabel_35.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_35.gridx = 5;
		gbc_lblNewLabel_35.gridy = 2;
		getContentPane().add(lblNewLabel_35, gbc_lblNewLabel_35);
		
		lblNewLabel_57 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_57 = new GridBagConstraints();
		gbc_lblNewLabel_57.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_57.gridx = 7;
		gbc_lblNewLabel_57.gridy = 2;
		getContentPane().add(lblNewLabel_57, gbc_lblNewLabel_57);
		
		JLabel lblNewLabel_3 = new JLabel("3");
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 3;
		getContentPane().add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		lblNewLabel_26 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_26 = new GridBagConstraints();
		gbc_lblNewLabel_26.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_26.gridx = 2;
		gbc_lblNewLabel_26.gridy = 3;
		getContentPane().add(lblNewLabel_26, gbc_lblNewLabel_26);
		
		lblNewLabel_47 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_47 = new GridBagConstraints();
		gbc_lblNewLabel_47.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_47.gridx = 3;
		gbc_lblNewLabel_47.gridy = 3;
		getContentPane().add(lblNewLabel_47, gbc_lblNewLabel_47);
		
		JLabel lblNewLabel_14 = new JLabel("14");
		GridBagConstraints gbc_lblNewLabel_14 = new GridBagConstraints();
		gbc_lblNewLabel_14.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_14.gridx = 4;
		gbc_lblNewLabel_14.gridy = 3;
		getContentPane().add(lblNewLabel_14, gbc_lblNewLabel_14);
		
		lblNewLabel_36 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_36 = new GridBagConstraints();
		gbc_lblNewLabel_36.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_36.gridx = 5;
		gbc_lblNewLabel_36.gridy = 3;
		getContentPane().add(lblNewLabel_36, gbc_lblNewLabel_36);
		
		lblNewLabel_58 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_58 = new GridBagConstraints();
		gbc_lblNewLabel_58.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_58.gridx = 7;
		gbc_lblNewLabel_58.gridy = 3;
		getContentPane().add(lblNewLabel_58, gbc_lblNewLabel_58);
		
		JLabel lblNewLabel_4 = new JLabel("4");
		GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
		gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_4.gridx = 0;
		gbc_lblNewLabel_4.gridy = 4;
		getContentPane().add(lblNewLabel_4, gbc_lblNewLabel_4);
		
		lblNewLabel_27 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_27 = new GridBagConstraints();
		gbc_lblNewLabel_27.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_27.gridx = 2;
		gbc_lblNewLabel_27.gridy = 4;
		getContentPane().add(lblNewLabel_27, gbc_lblNewLabel_27);
		
		lblNewLabel_48 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_48 = new GridBagConstraints();
		gbc_lblNewLabel_48.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_48.gridx = 3;
		gbc_lblNewLabel_48.gridy = 4;
		getContentPane().add(lblNewLabel_48, gbc_lblNewLabel_48);
		
		JLabel lblNewLabel_15 = new JLabel("15");
		GridBagConstraints gbc_lblNewLabel_15 = new GridBagConstraints();
		gbc_lblNewLabel_15.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_15.gridx = 4;
		gbc_lblNewLabel_15.gridy = 4;
		getContentPane().add(lblNewLabel_15, gbc_lblNewLabel_15);
		
		lblNewLabel_37 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_37 = new GridBagConstraints();
		gbc_lblNewLabel_37.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_37.gridx = 5;
		gbc_lblNewLabel_37.gridy = 4;
		getContentPane().add(lblNewLabel_37, gbc_lblNewLabel_37);
		
		lblNewLabel_59 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_59 = new GridBagConstraints();
		gbc_lblNewLabel_59.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_59.gridx = 7;
		gbc_lblNewLabel_59.gridy = 4;
		getContentPane().add(lblNewLabel_59, gbc_lblNewLabel_59);
		
		JLabel lblNewLabel_5 = new JLabel("5");
		GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
		gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_5.gridx = 0;
		gbc_lblNewLabel_5.gridy = 5;
		getContentPane().add(lblNewLabel_5, gbc_lblNewLabel_5);
		
		lblNewLabel_28 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_28 = new GridBagConstraints();
		gbc_lblNewLabel_28.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_28.gridx = 2;
		gbc_lblNewLabel_28.gridy = 5;
		getContentPane().add(lblNewLabel_28, gbc_lblNewLabel_28);
		
		lblNewLabel_49 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_49 = new GridBagConstraints();
		gbc_lblNewLabel_49.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_49.gridx = 3;
		gbc_lblNewLabel_49.gridy = 5;
		getContentPane().add(lblNewLabel_49, gbc_lblNewLabel_49);
		
		JLabel lblNewLabel_16 = new JLabel("16");
		GridBagConstraints gbc_lblNewLabel_16 = new GridBagConstraints();
		gbc_lblNewLabel_16.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_16.gridx = 4;
		gbc_lblNewLabel_16.gridy = 5;
		getContentPane().add(lblNewLabel_16, gbc_lblNewLabel_16);
		
	    lblNewLabel_38 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_38 = new GridBagConstraints();
		gbc_lblNewLabel_38.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_38.gridx = 5;
		gbc_lblNewLabel_38.gridy = 5;
		getContentPane().add(lblNewLabel_38, gbc_lblNewLabel_38);
		
		lblNewLabel_60 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_60 = new GridBagConstraints();
		gbc_lblNewLabel_60.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_60.gridx = 7;
		gbc_lblNewLabel_60.gridy = 5;
		getContentPane().add(lblNewLabel_60, gbc_lblNewLabel_60);
		
		JLabel lblNewLabel_6 = new JLabel("6");
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 6;
		getContentPane().add(lblNewLabel_6, gbc_lblNewLabel_6);
		
		lblNewLabel_29 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_29 = new GridBagConstraints();
		gbc_lblNewLabel_29.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_29.gridx = 2;
		gbc_lblNewLabel_29.gridy = 6;
		getContentPane().add(lblNewLabel_29, gbc_lblNewLabel_29);
		
		lblNewLabel_50 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_50 = new GridBagConstraints();
		gbc_lblNewLabel_50.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_50.gridx = 3;
		gbc_lblNewLabel_50.gridy = 6;
		getContentPane().add(lblNewLabel_50, gbc_lblNewLabel_50);
		
		JLabel lblNewLabel_17 = new JLabel("17");
		GridBagConstraints gbc_lblNewLabel_17 = new GridBagConstraints();
		gbc_lblNewLabel_17.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_17.gridx = 4;
		gbc_lblNewLabel_17.gridy = 6;
		getContentPane().add(lblNewLabel_17, gbc_lblNewLabel_17);
		
		lblNewLabel_39 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_39 = new GridBagConstraints();
		gbc_lblNewLabel_39.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_39.gridx = 5;
		gbc_lblNewLabel_39.gridy = 6;
		getContentPane().add(lblNewLabel_39, gbc_lblNewLabel_39);
		
		lblNewLabel_61 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_61 = new GridBagConstraints();
		gbc_lblNewLabel_61.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_61.gridx = 7;
		gbc_lblNewLabel_61.gridy = 6;
		getContentPane().add(lblNewLabel_61, gbc_lblNewLabel_61);
		
		JLabel lblNewLabel_7 = new JLabel("7");
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 7;
		getContentPane().add(lblNewLabel_7, gbc_lblNewLabel_7);
		
		lblNewLabel_30 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_30 = new GridBagConstraints();
		gbc_lblNewLabel_30.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_30.gridx = 2;
		gbc_lblNewLabel_30.gridy = 7;
		getContentPane().add(lblNewLabel_30, gbc_lblNewLabel_30);
		
		lblNewLabel_51 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_51 = new GridBagConstraints();
		gbc_lblNewLabel_51.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_51.gridx = 3;
		gbc_lblNewLabel_51.gridy = 7;
		getContentPane().add(lblNewLabel_51, gbc_lblNewLabel_51);
		
		JLabel lblNewLabel_18 = new JLabel("18");
		GridBagConstraints gbc_lblNewLabel_18 = new GridBagConstraints();
		gbc_lblNewLabel_18.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_18.gridx = 4;
		gbc_lblNewLabel_18.gridy = 7;
		getContentPane().add(lblNewLabel_18, gbc_lblNewLabel_18);
		
		lblNewLabel_40 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_40 = new GridBagConstraints();
		gbc_lblNewLabel_40.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_40.gridx = 5;
		gbc_lblNewLabel_40.gridy = 7;
		getContentPane().add(lblNewLabel_40, gbc_lblNewLabel_40);
		
		lblNewLabel_62 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_62 = new GridBagConstraints();
		gbc_lblNewLabel_62.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_62.gridx = 7;
		gbc_lblNewLabel_62.gridy = 7;
		getContentPane().add(lblNewLabel_62, gbc_lblNewLabel_62);
		
		JLabel lblNewLabel_8 = new JLabel("8");
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 8;
		getContentPane().add(lblNewLabel_8, gbc_lblNewLabel_8);
		
		lblNewLabel_31 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_31 = new GridBagConstraints();
		gbc_lblNewLabel_31.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_31.gridx = 2;
		gbc_lblNewLabel_31.gridy = 8;
		getContentPane().add(lblNewLabel_31, gbc_lblNewLabel_31);
		
		lblNewLabel_52 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_52 = new GridBagConstraints();
		gbc_lblNewLabel_52.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_52.gridx = 3;
		gbc_lblNewLabel_52.gridy = 8;
		getContentPane().add(lblNewLabel_52, gbc_lblNewLabel_52);
		
		JLabel lblNewLabel_19 = new JLabel("19");
		GridBagConstraints gbc_lblNewLabel_19 = new GridBagConstraints();
		gbc_lblNewLabel_19.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_19.gridx = 4;
		gbc_lblNewLabel_19.gridy = 8;
		getContentPane().add(lblNewLabel_19, gbc_lblNewLabel_19);
		
		lblNewLabel_41 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_41 = new GridBagConstraints();
		gbc_lblNewLabel_41.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_41.gridx = 5;
		gbc_lblNewLabel_41.gridy = 8;
		getContentPane().add(lblNewLabel_41, gbc_lblNewLabel_41);
		
		lblNewLabel_63 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_63 = new GridBagConstraints();
		gbc_lblNewLabel_63.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_63.gridx = 7;
		gbc_lblNewLabel_63.gridy = 8;
		getContentPane().add(lblNewLabel_63, gbc_lblNewLabel_63);
		
		JLabel lblNewLabel_9 = new JLabel("9");
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 9;
		getContentPane().add(lblNewLabel_9, gbc_lblNewLabel_9);
		
		lblNewLabel_32 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_32 = new GridBagConstraints();
		gbc_lblNewLabel_32.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_32.gridx = 2;
		gbc_lblNewLabel_32.gridy = 9;
		getContentPane().add(lblNewLabel_32, gbc_lblNewLabel_32);
		
		lblNewLabel_53 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_53 = new GridBagConstraints();
		gbc_lblNewLabel_53.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_53.gridx = 3;
		gbc_lblNewLabel_53.gridy = 9;
		getContentPane().add(lblNewLabel_53, gbc_lblNewLabel_53);
		
		JLabel lblNewLabel_20 = new JLabel("20");
		GridBagConstraints gbc_lblNewLabel_20 = new GridBagConstraints();
		gbc_lblNewLabel_20.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_20.gridx = 4;
		gbc_lblNewLabel_20.gridy = 9;
		getContentPane().add(lblNewLabel_20, gbc_lblNewLabel_20);
		
		lblNewLabel_42 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_42 = new GridBagConstraints();
		gbc_lblNewLabel_42.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_42.gridx = 5;
		gbc_lblNewLabel_42.gridy = 9;
		getContentPane().add(lblNewLabel_42, gbc_lblNewLabel_42);
		
		lblNewLabel_64 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_64 = new GridBagConstraints();
		gbc_lblNewLabel_64.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_64.gridx = 7;
		gbc_lblNewLabel_64.gridy = 9;
		getContentPane().add(lblNewLabel_64, gbc_lblNewLabel_64);
		
		JLabel lblNewLabel_10 = new JLabel("10");
		GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
		gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_10.gridx = 0;
		gbc_lblNewLabel_10.gridy = 10;
		getContentPane().add(lblNewLabel_10, gbc_lblNewLabel_10);
		
		lblNewLabel_33 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_33 = new GridBagConstraints();
		gbc_lblNewLabel_33.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_33.gridx = 2;
		gbc_lblNewLabel_33.gridy = 10;
		getContentPane().add(lblNewLabel_33, gbc_lblNewLabel_33);
		
		lblNewLabel_54 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_54 = new GridBagConstraints();
		gbc_lblNewLabel_54.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_54.gridx = 3;
		gbc_lblNewLabel_54.gridy = 10;
		getContentPane().add(lblNewLabel_54, gbc_lblNewLabel_54);
		
		JLabel lblNewLabel_21 = new JLabel("21");
		GridBagConstraints gbc_lblNewLabel_21 = new GridBagConstraints();
		gbc_lblNewLabel_21.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_21.gridx = 4;
		gbc_lblNewLabel_21.gridy = 10;
		getContentPane().add(lblNewLabel_21, gbc_lblNewLabel_21);
		
		lblNewLabel_43 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_43 = new GridBagConstraints();
		gbc_lblNewLabel_43.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_43.gridx = 5;
		gbc_lblNewLabel_43.gridy = 10;
		getContentPane().add(lblNewLabel_43, gbc_lblNewLabel_43);
		
		lblNewLabel_65 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_65 = new GridBagConstraints();
		gbc_lblNewLabel_65.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_65.gridx = 7;
		gbc_lblNewLabel_65.gridy = 10;
		getContentPane().add(lblNewLabel_65, gbc_lblNewLabel_65);
		
		JLabel lblNewLabel_11 = new JLabel("11");
		GridBagConstraints gbc_lblNewLabel_11 = new GridBagConstraints();
		gbc_lblNewLabel_11.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_11.gridx = 0;
		gbc_lblNewLabel_11.gridy = 11;
		getContentPane().add(lblNewLabel_11, gbc_lblNewLabel_11);
		
		lblNewLabel_34 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_34 = new GridBagConstraints();
		gbc_lblNewLabel_34.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_34.gridx = 2;
		gbc_lblNewLabel_34.gridy = 11;
		getContentPane().add(lblNewLabel_34, gbc_lblNewLabel_34);
		
		lblNewLabel_55 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_55 = new GridBagConstraints();
		gbc_lblNewLabel_55.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_55.gridx = 3;
		gbc_lblNewLabel_55.gridy = 11;
		getContentPane().add(lblNewLabel_55, gbc_lblNewLabel_55);
		
		JLabel lblNewLabel_22 = new JLabel("22");
		GridBagConstraints gbc_lblNewLabel_22 = new GridBagConstraints();
		gbc_lblNewLabel_22.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_22.gridx = 4;
		gbc_lblNewLabel_22.gridy = 11;
		getContentPane().add(lblNewLabel_22, gbc_lblNewLabel_22);
		
		lblNewLabel_44 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_44 = new GridBagConstraints();
		gbc_lblNewLabel_44.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_44.gridx = 5;
		gbc_lblNewLabel_44.gridy = 11;
		getContentPane().add(lblNewLabel_44, gbc_lblNewLabel_44);
		
		lblNewLabel_66 = new JLabel();
		GridBagConstraints gbc_lblNewLabel_66 = new GridBagConstraints();
		gbc_lblNewLabel_66.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel_66.gridx = 7;
		gbc_lblNewLabel_66.gridy = 11;
		getContentPane().add(lblNewLabel_66, gbc_lblNewLabel_66);
	}
}
