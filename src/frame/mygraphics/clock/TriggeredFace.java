package frame.mygraphics.clock;

import frame.mygraphics.MyCircle;
import frame.mygraphics.MyColoredGraphicsObject;
import frame.mygraphics.MyGraphicsObject;

import java.awt.*;

public class TriggeredFace extends MyColoredGraphicsObject {
    static final int EYE_SIZE = 10;
    static final int EYE_OUTLINE = 2;
    static final int MOUTH_SIZE = 30;
    static final int EYE_MOUTH_HORIZONTAL_OFFSET = 2;
    static final int EYE_MOUTH_VERTICAL_OFFSET = 5;

    MyGraphicsObject eye;
    MyGraphicsObject mouth;

    public TriggeredFace(Color c) {
        super(c, EYE_SIZE*2 + MOUTH_SIZE - EYE_MOUTH_HORIZONTAL_OFFSET*2, EYE_SIZE + MOUTH_SIZE - EYE_MOUTH_VERTICAL_OFFSET);
        eye = new OutlinedCircle(EYE_SIZE, EYE_OUTLINE, color, Color.WHITE);
        mouth = new MyCircle(color, MOUTH_SIZE);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        eye.drawAt(g, x, y);
        eye.drawAt(g, x + EYE_SIZE + MOUTH_SIZE - EYE_MOUTH_HORIZONTAL_OFFSET*2, y);
        mouth.drawAt(g, x + EYE_SIZE - EYE_MOUTH_HORIZONTAL_OFFSET, y + EYE_SIZE - EYE_MOUTH_VERTICAL_OFFSET);
    }
}
