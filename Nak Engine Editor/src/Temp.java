import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JRadioButton;
import java.awt.BorderLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.JTextArea;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class Temp {

	private JFrame frame;

	/**
	 * Launch the application.
	 *
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Temp window = new Temp();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Temp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		
		JPanel panelDisplay = new JPanel();
		panelDisplay.setBorder(new TitledBorder(null, "Display", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelSettA = new JPanel();
		panelSettA.setBorder(new TitledBorder(null, "Settings A", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		
		JPanel panelSettB = new JPanel();
		panelSettB.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Settings B", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JPanel panelConsole = new JPanel();
		panelConsole.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(panelConsole, GroupLayout.PREFERRED_SIZE, 649, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelDisplay, GroupLayout.PREFERRED_SIZE, 650, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSettA, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelSettB, GroupLayout.PREFERRED_SIZE, 324, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panelDisplay, GroupLayout.PREFERRED_SIZE, 481, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panelConsole, GroupLayout.PREFERRED_SIZE, 164, GroupLayout.PREFERRED_SIZE))
						.addComponent(panelSettA, GroupLayout.PREFERRED_SIZE, 653, GroupLayout.PREFERRED_SIZE)
						.addComponent(panelSettB, GroupLayout.PREFERRED_SIZE, 653, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JTextArea textArea = new JTextArea();
		textArea.setRows(7);
		textArea.setColumns(78);
		panelConsole.add(textArea);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(100, 100, 1336, 699);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
	}
}
