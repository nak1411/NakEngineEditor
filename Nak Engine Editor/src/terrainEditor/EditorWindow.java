package terrainEditor;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.DecimalFormat;

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

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import renderEngine.DisplayManager;

public class EditorWindow extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	private JFrame frame;
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
	private JTextField attX;
	private JTextField attY;
	private JTextField attZ;
	DecimalFormat df;
	
	Vector3f color;
	Vector3f attenuation;

	public EditorWindow() {
		init();
	}

	public void init() {

		frame = new JFrame("Nak Editor");
		frame.getContentPane().setBackground(UIManager.getColor("Button.background"));

		JPanel panelDisplay = new JPanel();
		panelDisplay.setBorder(new TitledBorder(null, "Viewport", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelLightSettings = new JPanel();
		panelLightSettings.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Light Settings", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panelSettB = new JPanel();
		panelSettB.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Settings B",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));

		JPanel panelConsole = new JPanel();
		panelConsole.setBorder(new TitledBorder(null, "Console", TitledBorder.LEADING, TitledBorder.TOP, null, null));

		JPanel panelStats = new JPanel();
		panelStats.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

		GroupLayout groupLayout = new GroupLayout(frame.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 1330, Short.MAX_VALUE)
							.addContainerGap())
						.addGroup(groupLayout.createSequentialGroup()
							.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
								.addComponent(panelConsole, GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
								.addComponent(panelDisplay, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE))
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelLightSettings, GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(panelSettB, GroupLayout.PREFERRED_SIZE, 260, GroupLayout.PREFERRED_SIZE)
							.addGap(8))))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING, false)
							.addComponent(panelSettB, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
							.addComponent(panelLightSettings, GroupLayout.PREFERRED_SIZE, 480, Short.MAX_VALUE))
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panelDisplay, GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panelConsole, 0, 0, Short.MAX_VALUE)
					.addGap(145)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
		);
		panelLightSettings.setLayout(new GridLayout(7, 1, 0, 0));
		lightHeightSlider.setToolTipText("Adjust PointLight height (0-100)");
		lightHeightSlider.setPaintLabels(true);
		lightHeightSlider.setValue(0);
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
		attenuationSlider.setMaximum(100);
		panelLightSettings.add(attenuationSlider);
		
		JPanel pnlLightClr = new JPanel();
		panelLightSettings.add(pnlLightClr);
		pnlLightClr.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Light Color", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GridBagLayout gbl_pnlLightClr = new GridBagLayout();
		gbl_pnlLightClr.columnWidths = new int[] {75, 75, 75};
		gbl_pnlLightClr.rowHeights = new int[] {0, 10};
		gbl_pnlLightClr.columnWeights = new double[]{1.0, 1.0, 1.0};
		gbl_pnlLightClr.rowWeights = new double[]{0.0, 0.0};
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

		JTextArea textArea = new JTextArea();
		textArea.setRows(7);
		textArea.setColumns(78);
		panelConsole.setLayout(new BorderLayout());
		panelConsole.add(textArea);
		frame.setVisible(true);
		frame.getContentPane().setLayout(groupLayout);
		frame.setBounds(0, 0, 1366, 720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelDisplay.setLayout(new BorderLayout());
		panelDisplay.add(this, BorderLayout.CENTER);

		this.setIgnoreRepaint(true);

		try {
			Display.setParent(this);
		} catch (LWJGLException e) {
			e.printStackTrace();
		}

		DisplayManager.createDisplay();

	}

	@Override
	public void run() {
		EditorWindow window = new EditorWindow();
		window.frame.setVisible(true);
	}

	public void updateFps(float fps) {
		lblFps.setText("FPS:" + Float.toString(fps) + "     ");
	}
	
	public float getLightHeight() {
		return lightHeightSlider.getValue();
	}
	
	public Vector3f getAttenuation() {
		return new Vector3f(1.0f, attenuationSlider.getValue() / 10.0f, attenuationSlider.getValue() / 10.0f);
	}
	
	public Vector3f getLightColor(){
		color = new Vector3f(0, 0, 0);
		if(!redC.getText().isEmpty() && !greenC.getText().isEmpty() && !blueC.getText().isEmpty()){
			color.x = Integer.parseInt(redC.getText());
			color.y = Integer.parseInt(greenC.getText());
			color.z = Integer.parseInt(blueC.getText());
			return color;
		}else{
			return new Vector3f(0, 0, 0);
		}
	}


	public void updateStats(float camX, float camY, float camZ, float mX, float mY, float mZ) {
		
		cameraX.setText("CamX: " + Float.toString((float) (Math.floor(camX * 100) / 100)) + "     ");
		cameraY.setText("CamY: " + Float.toString((float) (Math.floor(camY * 100) / 100)) + "     ");
		cameraZ.setText("CamZ: " + Float.toString((float) (Math.floor(camZ * 100) / 100)) + "     ");
		mouseX.setText("MouseX: " + Float.toString((float) (Math.floor(mX * 100) / 100)) + "     ");
		mouseY.setText("MouseY: " + Float.toString((float) (Math.floor(mY * 100) / 100)) + "     ");
		mouseZ.setText("MouseZ: " + Float.toString((float) (Math.floor(mZ * 100) / 100)) + "     ");
	}

}
