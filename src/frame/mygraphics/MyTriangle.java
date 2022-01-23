package frame.mygraphics;

import java.awt.*;

public class MyTriangle extends MyColoredGraphicsObject {
    public MyTriangle(Color c, int w, int h) {
        super(c, w, h);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillPolygon(new Polygon(
                new int[] {x+width/2, x, x+width},
                new int[] {y, y+height, y+height},
                3
        ));
    }
}
