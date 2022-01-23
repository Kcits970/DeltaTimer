package frame.mygraphics.clock;

import frame.mygraphics.MyCircle;
import frame.mygraphics.MyColoredGraphicsObject;
import frame.mygraphics.MyRoundRectangle;

import java.awt.*;

public class Markers extends MyColoredGraphicsObject {
    MyColoredGraphicsObject longMarker;
    MyColoredGraphicsObject shortMarker;
    static final int angleDistance = 15;

    public Markers(Color c, int size) {
        super(c, size, size);
        longMarker = new MyRoundRectangle(color, 8, 24);
        shortMarker = new MyCircle(color, 6);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        int centerX = x + width/2;
        int centerY = y + height/2;

        for (int i = 0; i < 360; i += angleDistance) {
            if (i % 90 == 0)
                longMarker.drawAtAnchor(g, centerX, y, ANCHOR_CENTER);
            else
                shortMarker.drawAtAnchor(g, centerX, y, ANCHOR_CENTER);

            ((Graphics2D) g).rotate(Math.toRadians(angleDistance), centerX, centerY);
        }
    }
}
