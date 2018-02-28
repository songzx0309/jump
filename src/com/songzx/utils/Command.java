package com.songzx.utils;

import java.io.IOException;
import java.util.Random;

public class Command {
    private static Runtime r = getRuntime();
    public static final String ADB_PREFIX = "./adb ";
    public static final String IMAGE_URL = "./tmp.png";
    public static final String JUMP = "shell input mouse swipe %d %d %d %d %d";

    private static Runtime getRuntime() {
        if(r == null) {
            r = Runtime.getRuntime();
        }
        return r;
    }

    public static void capture() {
        String cmd = "./print.sh";
        try {
            System.out.println(cmd);
            r.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void jump(double duration) {
        int a = 720 + new Random().nextInt(20);
        int b = 2300 + new Random().nextInt(20);
        int c = 720 + new Random().nextInt(20);
        int d = 2300 + new Random().nextInt(20);
        String cmd = ADB_PREFIX + String.format(JUMP, a, b, c, d, (int)duration);
        try {
            System.out.println(cmd);
            r.exec(cmd);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
