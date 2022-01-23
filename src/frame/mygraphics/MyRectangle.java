package frame.mygraphics;

import java.awt.*;

public class MyRectangle extends MyColoredGraphicsObject {
    public MyRectangle(Color c, int w, int h) {
        super(c, w, h);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
    }
}
