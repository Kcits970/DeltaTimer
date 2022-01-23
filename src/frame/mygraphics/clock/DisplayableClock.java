package frame.mygraphics.clock;

import frame.mygraphics.*;
import util.MillisFormatter;

import static frame.mygraphics.clock.ClockSizeConstants.*;

import java.awt.*;

public class DisplayableClock extends MyColoredGraphicsObject {
    MyGraphicsObject deltaKnob;
    MyGraphicsObject body;
    MyGraphicsObject minuteHand;
    MyGraphicsObject secondHand;
    MyGraphicsObject millisHand;
    MyGraphicsObject handOrigin;
    MyGraphicsObject normalFace;
    MyGraphicsObject triggeredFace;
    MyGraphicsObject markers;

    boolean triggered;
    long millisToDisplay = 0;

    public DisplayableClock(Color c) {
        super(c, CLOCK_SIZE, CLOCK_SIZE);
        deltaKnob = new DeltaKnob(color, DELTA_KNOB_WIDTH, OUTLINE_WIDTH);
        body = new OutlinedCircle(width, OUTLINE_WIDTH, color, Color.WHITE);
        minuteHand = new MyTriangle(color, MIN_HAND_WIDTH, MIN_HAND_LENGTH);
        secondHand = new MyTriangle(color, SEC_HAND_WIDTH, SEC_HAND_LENGTH);
        millisHand = new MyRectangle(color, MS_HAND_WIDTH, MS_HAND_LENGTH);
        handOrigin = new OutlinedCircle(ORIGIN_SIZE, ORIGIN_OUTLINE_WIDTH, color, Color.WHITE);
        normalFace = new NormalFace(color);
        triggeredFace = new TriggeredFace(color);
        markers = new Markers(color, MARKER_SIZE);
    }

    public void setFace(boolean triggered) {this.triggered = triggered;}

    public void setMillisToDisplay(long millisToDisplay) {
        this.millisToDisplay = millisToDisplay;
    }

    double getMillisRotation() {
        return 2*Math.PI * MillisFormatter.getMillisRemainder(millisToDisplay, false) / 1000.0;
    }

    double getSecondRotation() {
        return 2*Math.PI * MillisFormatter.getSecondRemainder(millisToDisplay, false) / 60.0;
    }

    double getMinuteRotation() {
        return 2*Math.PI * MillisFormatter.getMinute(millisToDisplay, false) / 60.0;
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        int centerX = x + width/2;
        int centerY = y + height/2;

        deltaKnob.drawAtAnchor(g, centerX, y + KNOB_OFFSET, ANCHOR_SOUTH);

        body.drawAtAnchor(g, centerX, centerY, ANCHOR_CENTER);

        minuteHand.drawAtAnchor(getRotatedGraphics(g, getMinuteRotation(), centerX, centerY), centerX, centerY, ANCHOR_SOUTH);
        secondHand.drawAtAnchor(getRotatedGraphics(g, getSecondRotation(), centerX, centerY), centerX, centerY, ANCHOR_SOUTH);
        millisHand.drawAtAnchor(getRotatedGraphics(g, getMillisRotation(), centerX, centerY), centerX, centerY, ANCHOR_SOUTH);

        handOrigin.drawAtAnchor(g, centerX, centerY, ANCHOR_CENTER);
        if (!triggered)
            normalFace.drawAtAnchor(g, centerX, centerY + FACE_OFFSET, ANCHOR_NORTH);
        else
            triggeredFace.drawAtAnchor(g, centerX, centerY + FACE_OFFSET, ANCHOR_NORTH);
        markers.drawAtAnchor(g, centerX, centerY, ANCHOR_CENTER);
    }
}
