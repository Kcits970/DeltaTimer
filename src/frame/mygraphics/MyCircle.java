package frame.mygraphics;

import frame.mygraphics.MyColoredGraphicsObject;

import java.awt.*;

public class MyCircle extends MyColoredGraphicsObject {
    public MyCircle(Color c, int size) {
        super(c, size, size);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x, y, width, height);
    }
}
