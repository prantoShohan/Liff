package Liff.Core;

import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.opengl.GL;



import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {

    private int width, height;
    private String title;

    private static Window window = null;

    private long windowReference;

    private Window(){
        this.width = 740;
        this.height = 620;
        this.title = "Liff of a pi";
    }

    public static Window get(){
        if(Window.window == null){
            Window.window = new Window();
        }
        return Window.window;
    }

    public long getWindowReference(){
        return windowReference;
    }

    public void init(){
        GLFWErrorCallback.createPrint(System.err).set();

        if (!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW!");
        }

        glfwDefaultWindowHints();
        glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);
        glfwWindowHint(GLFW_RESIZABLE, GLFW_TRUE);

        windowReference = glfwCreateWindow(width, height, title, NULL, NULL);
        if(windowReference == NULL){
            throw new RuntimeException("Failed to create window!");
        }

        glfwMakeContextCurrent(windowReference);
        glfwSwapInterval(1);
        glfwShowWindow(windowReference);

        glfwSetKeyCallback(windowReference, (windowReference, key, scancode, action, mods)->{
            if(key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE){
                glfwSetWindowShouldClose(windowReference, true);
            }
        });


        glfwSetCursorPosCallback(windowReference, MouseListener::mousePosCallback);
        glfwSetMouseButtonCallback(windowReference, MouseListener::mouseButtonCallback);
        glfwSetScrollCallback(windowReference, MouseListener::mouseScrollCallback);
        glfwSetKeyCallback(windowReference, KeyListener::keyCallback);

        GL.createCapabilities();

        glEnable(GL_BLEND);
        glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);


    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
