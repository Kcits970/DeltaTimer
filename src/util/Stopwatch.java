package util;

public class Stopwatch {
    private long markedTime;
    private boolean isRunning;
    private long elapsedMillis;

    public void mark() {
        markedTime = System.currentTimeMillis();
    }

    public boolean isMarked() {
        if (markedTime == 0)
            return false;
        else
            return true;
    }

    public boolean isRunning() {return isRunning;}

    public long getElapsedMillisAfterMark() {return System.currentTimeMillis() - markedTime;}

    public long getElapsedMillis() {return isRunning ? elapsedMillis + getElapsedMillisAfterMark() : elapsedMillis;}

    public void pause() {
        elapsedMillis += getElapsedMillisAfterMark();
        isRunning = false;
    }

    public void resume() {
        mark();
        isRunning = true;
    }

    public void reset() {
        markedTime = 0;
        elapsedMillis = 0;
        isRunning = false;
    }
}
