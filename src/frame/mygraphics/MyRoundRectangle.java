package frame.mygraphics;

import java.awt.*;

public class MyRoundRectangle extends MyColoredGraphicsObject {
    public MyRoundRectangle(Color c, int w, int h) {
        super(c, w, h);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        int arcSize = Math.min(width, height);
        g.setColor(color);
        g.fillRoundRect(x, y, width, height, arcSize, arcSize);
    }
}
