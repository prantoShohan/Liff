package Liff.Core;

import static org.lwjgl.glfw.GLFW.GLFW_PRESS;
import static org.lwjgl.glfw.GLFW.GLFW_RELEASE;

public class KeyListener {
    private static KeyListener instance = null;
    private boolean pressedKey[] = new boolean[350];

    private KeyListener(){}

    public static KeyListener get(){
        if(instance == null){
            instance = new KeyListener();
        }
        return instance;
    }

    public static void keyCallback(long window, int key, int scancode, int action, int mods){
        if (action == GLFW_PRESS){
            get().pressedKey[key] = true;
        }else if(action == GLFW_RELEASE){
            get().pressedKey[key] = false;
        }
    }

    public static boolean isKeypressed(int keyCode){
        return get().pressedKey[keyCode];
    }
}
