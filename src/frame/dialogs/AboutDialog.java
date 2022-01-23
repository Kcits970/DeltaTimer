package frame.dialogs;

import frame.MyContainable;
import frame.mygraphics.*;
import util.ColorConstants;

import javax.swing.*;
import java.awt.*;

public class AboutDialog extends JDialog implements MyContainable {
    AboutPanel aboutPanel;

    public AboutDialog(Frame owner) {
        super(owner, "About DeltaTimer", false);
        build();
    }

    @Override
    public void createComponents() {
        aboutPanel = new AboutPanel();
    }

    @Override
    public void addComponents() {
        getContentPane().add(aboutPanel);
    }

    @Override
    public void configureSettings() {
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setResizable(false);
        pack();
        setLocationRelativeTo(getOwner());
    }

    class AboutPanel extends JPanel {
        public AboutPanel() {
            setPreferredSize(new Dimension(640, 240));
        }

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            new HorizontalGroup(
                    HorizontalGroup.ALIGN_TOP,
                    new MyImage(ImageCollection.DELTA, true),
                    new BlankSpace(20, 0),
                    new VerticalGroup(
                            VerticalGroup.ALIGN_LEFT,
                            new MyString(ColorConstants.BLACK, "DeltaTimer", new Font("Consolas", Font.BOLD, 64), g),
                            new MyString(ColorConstants.BLACK, "Version 1.02", new Font("Consolas", Font.PLAIN, 18), g),
                            new BlankSpace(0, 30),
                            new MyString(ColorConstants.BLACK, "programmed by Taehyun Ahn(Kcits970)", new Font("Consolas", Font.ITALIC, 12), g),
                            new MyString(ColorConstants.BLACK, "(January 23, 2022)", new Font("Consolas", Font.ITALIC, 12), g),
                            new BlankSpace(0, 5),
                            new MyImage(ImageCollection.KCITS970, true)
                    )
            ).drawAtAnchor(g, getWidth()/2, getHeight()/2, MyGraphicsObject.ANCHOR_CENTER);
        }
    }
}
