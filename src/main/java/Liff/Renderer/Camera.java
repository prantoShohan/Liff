package Liff.Renderer;

import org.joml.Matrix4f;
import org.joml.Vector2f;
import org.joml.Vector3f;

public class Camera {
    private Matrix4f projection, view;
    public Vector2f position;

    public Camera(Vector2f pos){
        this.position = pos;
        this.projection = new Matrix4f();
        this.view = new Matrix4f();
        adjustProjection();
    }

    public void adjustProjection(){
        this.projection.identity();
        this.projection = projection.ortho(0.0f, 740.0f, 0.0f, 620.0f, 0.0f, 100.0f);

    }

    public Matrix4f getView(){
        Vector3f cameraFront = new Vector3f(0.0f, 0.0f, -1.0f);
        Vector3f cameraUp = new Vector3f(0.0f, 1.0f, 0.0f);
        this.view = this.view.identity();
        this.view = this.view.lookAt(new Vector3f(position.x, position.y, 20.0f),
                         cameraFront.add(position.x, position.y, 20.0f),
                         cameraUp);
        return this.view;
    }

    public Matrix4f getProjection(){
        return this.projection;
    }
}
