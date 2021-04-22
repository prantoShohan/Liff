import Liff.Renderable;
import Liff.Renderer.*;
import Liff.Shapes.Rectangle;
import Liff.Shapes.Shape;
import Liff.Views.View;
import org.joml.Vector2f;
import org.joml.Vector4f;
import qiwi.Solver;


import java.util.Arrays;
import java.util.List;


public class MyRender implements Renderable {
    private Shader shader;
    private Camera camera;


    @Override
    public void init() {

        shader = new Shader("src/main/resources/shaders/default.glsl");
        camera = new Camera(new Vector2f());
        shader.compile();
        Vector4f color = new Vector4f(1.0f, 1.0f, 0.0f, 1.0f);
//        Layout layout = new Layout();
//        View a = new View();
//        View b  = new View();
//        layout.addChild(a);
//        layout.addChild(b);
//        a.setConstraint()


        Renderer.submit(new Rectangle(100.0f, 100.0f, 500.0f, 500.0f, color), shader, camera);
        Renderer.processDrawCall();

    }

    @Override
    public void render(float dt) {
        Renderer.executeDrawCalls();
    }
}
