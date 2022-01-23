package frame.mygraphics;

import java.awt.*;

public abstract class MyGraphicsObject {
    public static final int ANCHOR_WEST = 1;
    public static final int ANCHOR_EAST = 2;
    public static final int ANCHOR_NORTH = 3;
    public static final int ANCHOR_SOUTH = 4;
    public static final int ANCHOR_CENTER = 5;
    public static final int ANCHOR_NORTHWEST = 6;
    public static final int ANCHOR_NORTHEAST = 7;
    public static final int ANCHOR_SOUTHWEST = 8;
    public static final int ANCHOR_SOUTHEAST = 9;

    public int width;
    public int height;

    public MyGraphicsObject() {}

    public MyGraphicsObject(int w, int h) {
        width = (w > 0) ? w : 0;
        height = (h > 0) ? h : 0;
    }

    public abstract void drawAt(Graphics g, int x, int y); //drawAt must always draw the object anchored to the NW position.

    public void drawAtAnchor(Graphics g, int x, int y, int anchor) {
        switch (anchor) {
            case ANCHOR_WEST -> drawAt(g, x, y - height/2);
            case ANCHOR_EAST -> drawAt(g, x - width, y - height/2);
            case ANCHOR_NORTH -> drawAt(g, x - width/2, y);
            case ANCHOR_SOUTH -> drawAt(g, x - width/2, y - height);
            case ANCHOR_CENTER -> drawAt(g, x - width/2, y - height/2);
            case ANCHOR_NORTHWEST -> drawAt(g, x, y);
            case ANCHOR_NORTHEAST -> drawAt(g, x - width, y);
            case ANCHOR_SOUTHWEST -> drawAt(g, x, y - height);
            case ANCHOR_SOUTHEAST -> drawAt(g, x - width, y - height);
        }
    }

    public static Graphics getRotatedGraphics(Graphics g, double theta, int x, int y) {
        Graphics2D rotated = (Graphics2D) g.create();
        rotated.rotate(theta, x, y);
        return rotated;
    }
}
