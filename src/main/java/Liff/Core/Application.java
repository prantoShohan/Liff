package Liff.Core;

import Liff.Renderer.Renderer;
import org.lwjgl.Version;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;

public class Application {
    private String title;
    private Window window;
    private Renderable renderframe = null;

    public Application(int width, int height, String name){
        this.title = name;
        window = Window.get();
        window.setHeight(height);
        window.setWidth(width);
        window.setTitle(this.title);
    }
    public void run(){
        System.out.println("Hello Liff "+ Version.getVersion()+"!");

        Renderer.get();

        window.init();
        renderframe.init();
        loop();

        // Free the window callbacks and destroy the window
        glfwFreeCallbacks(window.getWindowReference());
        glfwDestroyWindow(window.getWindowReference());

        // Terminate GLFW and free the error callback
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }

    private void loop(){
        float beginTime = (float)glfwGetTime();
        float endTime;
        float dt = 0.0f;

        while(!glfwWindowShouldClose(window.getWindowReference())){
            glfwPollEvents();
            glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
            glClear(GL_COLOR_BUFFER_BIT);
            if(dt>0) render(dt);
            glfwSwapBuffers(window.getWindowReference());

            endTime = (float)glfwGetTime();
            dt = endTime - beginTime;
            beginTime = endTime;
        }
    }

    public void setRenderObject(Renderable renderable){
        this.renderframe = renderable;
    }

    private void render(float dt){
        if(this.renderframe != null){
            this.renderframe.render(dt);
        }

    }
}
