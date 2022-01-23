package frame.mygraphics.clock;

import frame.mygraphics.MyColoredGraphicsObject;
import frame.mygraphics.MyTriangle;

import java.awt.*;

public class DeltaKnob extends MyColoredGraphicsObject {
    MyTriangle outerTriangle;
    MyTriangle innerTriangle;
    int outlineWidth;

    public DeltaKnob(Color c, int width, int outlineWidth) {
        super(c, width, (int) (width * Math.sqrt(3)/2));
        outerTriangle = new MyTriangle(c, width, (int) (width * Math.sqrt(3)/2));
        innerTriangle = new MyTriangle(Color.WHITE, outerTriangle.width/2, outerTriangle.height/2);
        this.outlineWidth = outlineWidth;
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        outerTriangle.drawAt(g, x, y);
        innerTriangle.drawAt(g, x + (int) (outlineWidth * Math.sqrt(3)), y + outerTriangle.height - innerTriangle.height - outlineWidth);
    }
}
