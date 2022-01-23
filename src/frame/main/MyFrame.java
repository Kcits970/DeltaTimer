package frame.main;

import frame.*;
import frame.dialogs.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements MyContainable {
	ClockPanel clockPanel;
	LapPanel lapPanel;
	JButton lapButton;
	JButton resetButton;

	AboutDialog aboutDialog;

	ActionListener lapAction = e -> lapPanel.addNewLap(clockPanel.stopwatch.getElapsedMillis());
	ActionListener resetAction = e -> {clockPanel.reset(); lapPanel.reset();};

	public MyFrame() {
		build();
	}

	@Override
	public void createComponents() {
		clockPanel = new ClockPanel();
		lapPanel = new LapPanel();
		lapButton = new JButton("Lap");
		resetButton = new JButton("Reset");

		aboutDialog = new AboutDialog(this);
	}

	@Override
	public void addComponents() {
		Container c = getContentPane();
		c.setLayout(new GridBagLayout());
		GridBagConstraints constraints = new GridBagConstraints();
		constraints.insets = new Insets(5, 5, 5, 5);
		constraints.fill = GridBagConstraints.BOTH;

		constraints.gridx = 0;
		constraints.gridy = 0;
		constraints.gridheight = 3;
		c.add(clockPanel, constraints);

		constraints.gridx = 1;
		constraints.gridheight = 1;
		c.add(lapPanel, constraints);

		constraints.gridy = 1;
		c.add(MyFrameTools.addHorizontally(null, false, lapButton, resetButton), constraints);

		setContentPane(MyFrameTools.addComponentWithEdgeSpacing(null, c, 5));
	}

	@Override
	public void bindActions() {
		lapButton.addActionListener(lapAction);
		resetButton.addActionListener(resetAction);
	}

	@Override
	public void configureSettings() {
		lapButton.setFocusable(false);
		resetButton.setFocusable(false);

		setTitle("DeltaTimer");
		setJMenuBar(new MyMenuBar());
		pack();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
	}

	class MyMenuBar extends JMenuBar implements MyContainable {
		JMenuItem exitMenuItem;
		JMenuItem runMenuItem;
		JMenuItem lapMenuItem;
		JMenuItem resetMenuItem;
		JMenuItem aboutMenuItem;

		public MyMenuBar() {
			build();
		}

		@Override
		public void createComponents() {
			exitMenuItem = new JMenuItem("Exit");
			runMenuItem = new JMenuItem("Run");
			lapMenuItem = new JMenuItem("Lap");
			resetMenuItem = new JMenuItem("Reset");
			aboutMenuItem = new JMenuItem("About DeltaTimer");
		}

		@Override
		public void addComponents() {
			JMenu fileMenu = new JMenu("File");
			fileMenu.add(exitMenuItem);

			JMenu editMenu = new JMenu("Actions");
			editMenu.add(lapMenuItem);
			editMenu.add(resetMenuItem);

			JMenu aboutMenu = new JMenu("About");
			aboutMenu.add(aboutMenuItem);

			add(fileMenu);
			add(editMenu);
			add(aboutMenu);
		}

		@Override
		public void bindActions() {
			exitMenuItem.addActionListener(e -> System.exit(0));
			lapMenuItem.addActionListener(lapAction);
			resetMenuItem.addActionListener(resetAction);
			aboutMenuItem.addActionListener(e -> aboutDialog.setVisible(true));
		}

		@Override
		public void configureSettings() {
			exitMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.CTRL_MASK));
			lapMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_L, ActionEvent.CTRL_MASK));
			resetMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		}
	}
}