package util;

public class MillisFormatter {
    public static final long MAX_DISPLAYABLE_MILLIS = 5999999L;
    public static final int M_S_MS_FORMAT = 1;
    public static final int COLON_FORMAT = 2;

    public static int getMillisRemainder(long ms, boolean limit) {
        return (limit && ms >= MAX_DISPLAYABLE_MILLIS) ? 999 : (int) (ms%1000);
    }

    public static int getSecondRemainder(long ms, boolean limit) {
        return (limit && ms >= MAX_DISPLAYABLE_MILLIS) ? 59 : (int) (ms/1000 % 60);
    }

    public static int getMinute(long ms, boolean limit) {
        return (limit && ms >= MAX_DISPLAYABLE_MILLIS) ? 99 : (int) (ms/1000/60);
    }

    public static String millisToString(long ms, final int format) {
        if (format == M_S_MS_FORMAT)
            return String.format("%dm %ds %dms", getMinute(ms, true), getSecondRemainder(ms, true), getMillisRemainder(ms, true));
        else if (format == COLON_FORMAT)
            return String.format("%02d:%02d", getMinute(ms, true), getSecondRemainder(ms, true));
        else
            return "";
    }
}
