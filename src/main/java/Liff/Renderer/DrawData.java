package Liff.Renderer;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;

import java.util.List;

public class DrawData {
    public Shape shape;
    public Shader shader;
    public Camera camera;


    public DrawData(Shape shape, Shader shader, Camera camera) {
        this.shape = shape;
        this.shader = shader;
        this.camera = camera;
    }

}
