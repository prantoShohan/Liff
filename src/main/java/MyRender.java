import Liff.Renderable;
import Liff.Renderer.*;
import Liff.Shapes.Rectangle;
import org.joml.Vector2f;
import org.joml.Vector4f;


public class MyRender implements Renderable {
    private Shader shader;
    private Camera camera;


    @Override
    public void init() {

        shader = new Shader("src/main/resources/shaders/default.glsl");
        camera = new Camera(new Vector2f());
        shader.compile();
        Vector4f color = new Vector4f(1.0f, 1.0f, 0.0f, 1.0f);
        Vector4f color1 = new Vector4f(1.0f, 0.0f, 0.3f, 1.0f);
        Vector4f color2 = new Vector4f(1.0f, 0.5f, 0.3f, 1.0f);
//        Layout layout = new Layout();
//        View a = new View();
//        View b  = new View();
//        layout.addChild(a);
//        layout.addChild(b);
//        a.setLeftConstraint(new LeftConstraint(layout));
//        a.setRightConstraint(new RightConstraint(b));


        Renderer.submit(new Rectangle(100.0f, 100.0f, 200.0f, 200.0f, color), shader, camera);
        Renderer.submit(new Rectangle(200.0f, 200.0f, 500.0f, 500.0f, color1), shader, camera);
        Renderer.submit(new Rectangle(300.0f, 300.0f, 500.0f, 500.0f, color2), shader, camera);
        Renderer.processDrawCall();

    }

    @Override
    public void render(float dt) {
        Renderer.executeDrawCalls();
    }
}
