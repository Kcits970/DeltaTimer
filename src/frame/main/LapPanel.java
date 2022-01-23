package frame.main;

import frame.*;
import frame.mygraphics.*;
import util.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LapPanel extends JPanel implements MyContainable, Resettable {
	JPanel nestedPanels;
	List<Long> laps;
	long shortestLap;
	long longestLap;

	public LapPanel() {
		build();
	}

	@Override
	public void createComponents() {
		nestedPanels = new JPanel();
		laps = new ArrayList<>();
		laps.add(0L);
	}

	@Override
	public void addComponents() {
		JPanel spacedPanel = new JPanel();
		spacedPanel.setBackground(Color.WHITE);

		JScrollPane scrollPane = new JScrollPane(MyFrameTools.addComponentWithEdgeSpacing(spacedPanel, nestedPanels, 5),
				ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setPreferredSize(new Dimension(280, 400));

		MyFrameTools.addComponentWithEdgeSpacing(this, scrollPane, 5);
	}

	@Override
	public void configureSettings() {
		nestedPanels.setBackground(Color.WHITE);
		nestedPanels.setLayout(new BoxLayout(nestedPanels, BoxLayout.Y_AXIS));
		setBorder(MyFrameTools.createMyTitledBorder("Laps"));
	}

	void addNewLap(long ms) {
		laps.add(ms);
		setOutliers();
		drawLapDetailPanels();
	}

	void setOutliers() {
		long difference = laps.get(laps.size() - 1) - laps.get(laps.size() - 2);
		if (difference != 0) {
			shortestLap = (shortestLap == 0) ? difference : Math.min(shortestLap, difference);
			longestLap = Math.max(longestLap, difference);
		}
	}

	void drawLapDetailPanels() {
		nestedPanels.removeAll();

		for (int i = laps.size() - 1; i >= 1; i--)
			nestedPanels.add(new LapDetailPanel(i, laps.get(i), laps.get(i) - laps.get(i - 1)));

		nestedPanels.revalidate();
		nestedPanels.repaint();
	}

	@Override
	public void reset() {
		laps.clear();
		laps.add(0L);
		shortestLap = 0;
		longestLap = 0;
		drawLapDetailPanels();
	}

	class LapDetailPanel extends JPanel {
		static final int EDGE_OFFSET = 5;
		long lappedTime;
		long incrementedTime;

		public LapDetailPanel(int nth, long ms, long increment) {
			lappedTime = ms;
			incrementedTime = increment;

			setOpaque(false);
			setBorder(MyFrameTools.createMyTitledBorder("Lap " + nth));
			setPreferredSize(new Dimension(0, 64));
			setMaximumSize(new Dimension(Short.MAX_VALUE, 64));
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

			new MyString(
					ColorConstants.BLACK,
					MillisFormatter.millisToString(lappedTime, MillisFormatter.M_S_MS_FORMAT),
					new Font("Consolas", Font.PLAIN, 24),
					g
			).drawAtAnchor(g, EDGE_OFFSET, getHeight()/2 + EDGE_OFFSET, MyGraphicsObject.ANCHOR_WEST);

			new MyString(
					(incrementedTime != 0) ? ((incrementedTime == shortestLap) ? ColorConstants.BLUE : (incrementedTime == longestLap) ? ColorConstants.RED : ColorConstants.BLACK) : ColorConstants.BLACK,
					(incrementedTime != 0) ? "+" + MillisFormatter.millisToString(incrementedTime, MillisFormatter.M_S_MS_FORMAT) : "---",
					new Font("Consolas", Font.BOLD | Font.ITALIC, 12),
					g
			).drawAtAnchor(g, getWidth() - EDGE_OFFSET, getHeight() - EDGE_OFFSET, MyGraphicsObject.ANCHOR_SOUTHEAST);
		}
	}
}