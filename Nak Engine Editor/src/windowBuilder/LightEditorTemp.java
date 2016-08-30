package windowBuilder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
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
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class LightEditorTemp {

	private JFrame lightEditorPanel;
	JLabel lblFps = new JLabel();
	JLabel cameraX = new JLabel();
	JLabel cameraY = new JLabel();
	JLabel cameraZ = new JLabel();
	JLabel mouseX = new JLabel();
	JLabel mouseY = new JLabel();
	JLabel mouseZ = new JLabel();
	JSlider lightHeightSlider = new JSlider();
	JSlider attenuationSlider = new JSlider();
	private JTextField redC;
	private JTextField greenC;
	private JTextField blueC;

	/**
	 * Create the application.
	 */
	public LightEditorTemp() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		lightEditorPanel = new JFrame("Nak Light Editor");
		lightEditorPanel.getContentPane().setBackground(UIManager.getColor("Button.background"));

		JPanel panelDisplay = new JPanel();
		panelDisplay.setBorder(new TitledBorder(null, "Viewport", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelLightSettings = new JPanel();
		panelLightSettings.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Light Settings", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panelSettB = new JPanel();
		panelSettB.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Settings B", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panelConsole = new JPanel();
		panelConsole.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelStats = new JPanel();
		panelStats.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		GroupLayout groupLayout = new GroupLayout(lightEditorPanel.getContentPane());
		groupLayout.setHorizontalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addContainerGap()
				.addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addComponent(panel, GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE).addContainerGap()).addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addComponent(panelConsole, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE).addComponent(panelDisplay, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)).addPreferredGap(ComponentPlacement.RELATED).addComponent(panelLightSettings, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE).addPreferredGap(ComponentPlacement.RELATED).addComponent(panelSettB, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE).addGap(8)))));
		groupLayout.setVerticalGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createSequentialGroup().addGroup(groupLayout.createParallelGroup(Alignment.LEADING).addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false).addComponent(panelSettB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE).addComponent(panelLightSettings, GroupLayout.PREFERRED_SIZE, 480, Short.MAX_VALUE)).addGroup(groupLayout.createSequentialGroup().addContainerGap().addComponent(panelDisplay, GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE))).addPreferredGap(ComponentPlacement.RELATED).addComponent(panelConsole, 0, 0, Short.MAX_VALUE).addGap(145).addComponent(panel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)));
		panelLightSettings.setLayout(new GridLayout(7, 1, 0, 0));
		lightHeightSlider.setValue(0);
		lightHeightSlider.setToolTipText("Adjust PointLight height (0-100)");
		lightHeightSlider.setPaintLabels(true);

		lightHeightSlider.setPaintTicks(true);
		lightHeightSlider.setBorder(new TitledBorder(null, "Point Light Height", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		lightHeightSlider.setPaintTicks(true);
		lightHeightSlider.setMajorTickSpacing(10);
		lightHeightSlider.setMinorTickSpacing(5);
		panelLightSettings.add(lightHeightSlider);

		attenuationSlider.setValue(0);

		attenuationSlider.setBorder(new TitledBorder(null, "Light Attenuation", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		attenuationSlider.setPaintTicks(true);
		attenuationSlider.setPaintLabels(true);
		attenuationSlider.setMajorTickSpacing(10);
		attenuationSlider.setMinorTickSpacing(5);
		attenuationSlider.setMaximum(10);
		panelLightSettings.add(attenuationSlider);

		JPanel pnlLightClr = new JPanel();
		panelLightSettings.add(pnlLightClr);
		pnlLightClr.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Light Color", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_pnlLightClr = new GridBagLayout();
		gbl_pnlLightClr.columnWidths = new int[] { 75, 75, 75 };
		gbl_pnlLightClr.rowHeights = new int[] { 0, 10 };
		gbl_pnlLightClr.columnWeights = new double[] { 1.0, 1.0, 1.0 };
		gbl_pnlLightClr.rowWeights = new double[] { 0.0, 0.0 };
		pnlLightClr.setLayout(gbl_pnlLightClr);

		JLabel redLbl = new JLabel("RED");
		GridBagConstraints gbc_redLbl = new GridBagConstraints();
		gbc_redLbl.insets = new Insets(0, 0, 5, 5);
		gbc_redLbl.gridx = 0;
		gbc_redLbl.gridy = 0;
		pnlLightClr.add(redLbl, gbc_redLbl);

		JLabel greenLbl = new JLabel("GREEN");
		GridBagConstraints gbc_greenLbl = new GridBagConstraints();
		gbc_greenLbl.insets = new Insets(0, 0, 5, 5);
		gbc_greenLbl.gridx = 1;
		gbc_greenLbl.gridy = 0;
		pnlLightClr.add(greenLbl, gbc_greenLbl);

		JLabel blueLbl = new JLabel("BLUE");
		GridBagConstraints gbc_blueLbl = new GridBagConstraints();
		gbc_blueLbl.insets = new Insets(0, 0, 5, 0);
		gbc_blueLbl.gridx = 2;
		gbc_blueLbl.gridy = 0;
		pnlLightClr.add(blueLbl, gbc_blueLbl);

		redC = new JTextField();
		redC.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_redC = new GridBagConstraints();
		gbc_redC.insets = new Insets(0, 0, 0, 5);
		gbc_redC.fill = GridBagConstraints.HORIZONTAL;
		gbc_redC.gridx = 0;
		gbc_redC.gridy = 1;
		pnlLightClr.add(redC, gbc_redC);
		redC.setColumns(1);

		greenC = new JTextField();
		greenC.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_greenC = new GridBagConstraints();
		gbc_greenC.insets = new Insets(0, 0, 0, 5);
		gbc_greenC.fill = GridBagConstraints.HORIZONTAL;
		gbc_greenC.gridx = 1;
		gbc_greenC.gridy = 1;
		pnlLightClr.add(greenC, gbc_greenC);
		greenC.setColumns(1);

		blueC = new JTextField();
		blueC.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_blueC = new GridBagConstraints();
		gbc_blueC.fill = GridBagConstraints.HORIZONTAL;
		gbc_blueC.gridx = 2;
		gbc_blueC.gridy = 1;
		pnlLightClr.add(blueC, gbc_blueC);
		blueC.setColumns(1);

		panel.add(cameraX);
		panel.add(cameraY);
		panel.add(cameraZ);
		panel.add(mouseX);
		panel.add(mouseY);
		panel.add(mouseZ);
		panel.add(lblFps);

		JMenuBar menuBar = new JMenuBar();
		lightEditorPanel.setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnFile.add(mntmExit);
		mntmExit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				lightEditorPanel.dispose();
			}
		});

		JMenu mnEditor = new JMenu("Editor");
		menuBar.add(mnEditor);

		JMenuItem mntmTerrainEditor = new JMenuItem("Terrain Editor...");
		mnEditor.add(mntmTerrainEditor);

		JMenuItem mntmEntityEditor = new JMenuItem("Entity Editor");
		mnEditor.add(mntmEntityEditor);

		JMenuItem mntmLightEditor = new JMenuItem("Light Editor...");
		mnEditor.add(mntmLightEditor);

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

		JTextArea textArea = new JTextArea();
		textArea.setRows(7);
		textArea.setColumns(78);
		panelConsole.setLayout(new BorderLayout());
		panelConsole.add(textArea);
		lightEditorPanel.setVisible(true);
		lightEditorPanel.getContentPane().setLayout(groupLayout);
		lightEditorPanel.setBounds(0, 0, 1366, 720);
		lightEditorPanel.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}
