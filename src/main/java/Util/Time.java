package Util;

public class Time {
    public static float timestarted = System.nanoTime();
    public static float getTime(){return (float)((System.nanoTime() - timestarted)*1E-9);}
}
