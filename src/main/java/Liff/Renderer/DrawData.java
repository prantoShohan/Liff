package Liff.Renderer;

import Liff.Shapes.Shape;

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
