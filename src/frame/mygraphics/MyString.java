package frame.mygraphics;

import java.awt.*;

public class MyString extends MyColoredGraphicsObject {
    String string;
    Font font;

    public MyString(Color c, String s, Font f, Graphics g) {
        super(
                c,
                (int) g.getFontMetrics(f).getStringBounds(s, g).getWidth(),
                g.getFontMetrics(f).getAscent() + g.getFontMetrics(f).getDescent()
        );

        font = f;
        string = s;
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        g.setFont(font);
        g.setColor(color);
        g.drawString(string, x, y + g.getFontMetrics().getAscent());
    }
}
