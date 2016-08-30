package windowBuilder;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class MainEditorTemp {

	private JFrame mainEditorPanel;
	JLabel lblFps = new JLabel();
	JLabel cameraX = new JLabel();
	JLabel cameraY = new JLabel();
	JLabel cameraZ = new JLabel();
	JLabel mouseX = new JLabel();
	JLabel mouseY = new JLabel();
	JLabel mouseZ = new JLabel();
	TerrainEditorTemp terrainEditorPanel;
	EntityEditorTemp entityEditorPanel;
	LightEditorTemp lightEditorPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainEditorTemp main = new MainEditorTemp();
					main.mainEditorPanel.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainEditorTemp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		mainEditorPanel = new JFrame("Nak Editor");
		mainEditorPanel.getContentPane().setBackground(UIManager.getColor("Button.background"));

		JPanel panelDisplay = new JPanel();
		panelDisplay.setBorder(new TitledBorder(null, "Viewport", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelStats = new JPanel();
		panelStats.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		GroupLayout groupLayout = new GroupLayout(mainEditorPanel.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap().addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE).addComponent(panelDisplay, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)).addContainerGap()));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup().addContainerGap().addComponent(panelDisplay, GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE).addPreferredGap(ComponentPlacement.RELATED).addComponent(panel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)));

		panel.add(cameraX);
		panel.add(cameraY);
		panel.add(cameraZ);
		panel.add(mouseX);
		panel.add(mouseY);
		panel.add(mouseZ);
		panel.add(lblFps);

		JMenuBar menuBar = new JMenuBar();
		mainEditorPanel.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		JMenu mnEditor = new JMenu("Editor");
		menuBar.add(mnEditor);

		JMenuItem mntmTerrainEditor = new JMenuItem("Terrain Editor...");
		mnEditor.add(mntmTerrainEditor);
		mntmTerrainEditor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				terrainEditorPanel = new TerrainEditorTemp();
			}
		});

		JMenuItem mntmEntityEditor = new JMenuItem("Entity Editor");
		mnEditor.add(mntmEntityEditor);
		mntmEntityEditor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				entityEditorPanel = new EntityEditorTemp();
			}
		});

		JMenuItem mntmLightEditor = new JMenuItem("Light Editor...");
		mnEditor.add(mntmLightEditor);
		mntmLightEditor.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lightEditorPanel = new LightEditorTemp();
			}
		});

		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);

		JMenuItem mntmNewMenuItem = new JMenuItem("General...");
		mnOptions.add(mntmNewMenuItem);

		JMenuItem mntmLighting = new JMenuItem("Lighting...");
		mnOptions.add(mntmLighting);

		JMenuItem mntmGraphics = new JMenuItem("Graphics...");
		mnOptions.add(mntmGraphics);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		mainEditorPanel.setVisible(true);
		mainEditorPanel.getContentPane().setLayout(groupLayout);
		mainEditorPanel.setBounds(0, 0, 1366, 720);
		mainEditorPanel.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
