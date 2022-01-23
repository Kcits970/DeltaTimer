package frame.main;

import frame.*;
import frame.mygraphics.*;
import frame.mygraphics.clock.DisplayableClock;
import util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClockPanel extends JPanel implements MyContainable, Resettable {
	static final int CLOCK_TEXT_DISTANCE = 10;
	static final int REFRESH_RATE = 16;

	DisplayableClock displayableClock;
	Stopwatch stopwatch;
	Timer refresher;

	public ClockPanel() {build();}

	@Override
	public void createComponents() {
		displayableClock = new DisplayableClock(ColorConstants.BLACK);
		stopwatch = new Stopwatch();
		refresher = new Timer(0, null);
	}

	@Override
	public void addComponents() {}

	@Override
	public void bindActions() {
		refresher.setDelay(REFRESH_RATE);
		refresher.addActionListener(e -> repaint());

		getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "keyPressed");
		getActionMap().put("keyPressed", new AbstractAction() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (!stopwatch.isRunning()) {
					stopwatch.resume();
					refresher.start();
				} else {
					stopwatch.pause();
					refresher.stop();
					repaint();
				}
			}
		});
	}

	@Override
	public void configureSettings() {
		setPreferredSize(new Dimension(300, 0));
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		displayableClock.setMillisToDisplay(stopwatch.getElapsedMillis());
		displayableClock.setFace(stopwatch.isRunning());

		MyString timeString = new MyString(
				ColorConstants.BLACK,
				MillisFormatter.millisToString(stopwatch.getElapsedMillis(), MillisFormatter.COLON_FORMAT),
				new Font("Consolas", Font.BOLD, 54),
				g);
		MyString millisString = new MyString(
				ColorConstants.BLACK,
				String.format("%03d", MillisFormatter.getMillisRemainder(stopwatch.getElapsedMillis(), true)),
				new Font("Consolas", Font.PLAIN, 16),
				g);

		int centerX = getWidth()/2;
		int centerY = getHeight()/2;

		displayableClock.drawAtAnchor(g, centerX, centerY, MyGraphicsObject.ANCHOR_CENTER);
		timeString.drawAtAnchor(g, centerX, centerY + displayableClock.height/2 + CLOCK_TEXT_DISTANCE, MyGraphicsObject.ANCHOR_NORTH);
		millisString.drawAtAnchor(g, centerX + timeString.width/2, centerY + displayableClock.height/2 + CLOCK_TEXT_DISTANCE + timeString.height, MyGraphicsObject.ANCHOR_SOUTHWEST);
	}

	@Override
	public void reset() {
		refresher.stop();
		stopwatch.reset();
		repaint();
	}
}