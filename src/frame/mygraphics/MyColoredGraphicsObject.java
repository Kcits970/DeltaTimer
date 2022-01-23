package frame.mygraphics;

import java.awt.*;

public abstract class MyColoredGraphicsObject extends MyGraphicsObject {
    public Color color;

    public MyColoredGraphicsObject(Color c) {
        color = c;
    }

    public MyColoredGraphicsObject(Color c, int w, int h) {
        super(w, h);
        color = c;
    }
}
