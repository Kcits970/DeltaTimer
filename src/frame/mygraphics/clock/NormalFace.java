package frame.mygraphics.clock;

import frame.mygraphics.*;

import java.awt.*;

public class NormalFace extends MyColoredGraphicsObject {
    static final int EYE_SIZE = 10;
    static final int MOUTH_WIDTH = 26;
    static final int MOUTH_HEIGHT = 6;
    static final int EYE_MOUTH_OFFSET = 3;

    MyGraphicsObject eye;
    MyGraphicsObject mouth;

    public NormalFace(Color c) {
        super(c, EYE_SIZE*2 + MOUTH_WIDTH, EYE_SIZE + EYE_MOUTH_OFFSET + MOUTH_HEIGHT);
        eye = new MyCircle(color, EYE_SIZE);
        mouth = new MyRoundRectangle(color, MOUTH_WIDTH, MOUTH_HEIGHT);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        eye.drawAt(g, x, y);
        eye.drawAt(g, x + EYE_SIZE + MOUTH_WIDTH, y);
        mouth.drawAt(g, x + EYE_SIZE, y + EYE_SIZE + EYE_MOUTH_OFFSET);
    }
}
