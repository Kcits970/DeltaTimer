package frame.mygraphics.clock;

import frame.mygraphics.MyCircle;
import frame.mygraphics.MyGraphicsObject;

import java.awt.*;

public class OutlinedCircle extends MyGraphicsObject {
    MyCircle outerCircle;
    MyCircle innerCircle;
    int outlineWidth;

    public OutlinedCircle(int size, int outlineWidth, Color outerColor, Color innerColor) {
        super(size, size);
        this.outlineWidth = outlineWidth;
        outerCircle = new MyCircle(outerColor, size + outlineWidth);
        innerCircle = new MyCircle(innerColor, size - outlineWidth);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        outerCircle.drawAt(g, x - outlineWidth/2, y - outlineWidth/2);
        innerCircle.drawAt(g, x + outlineWidth/2, y + outlineWidth/2);
    }
}
