import Liff.Renderable;
import Liff.Renderer.*;
import Liff.Shapes.RectShape;
import Liff.Views.ConstraintLayout.ConstraintLayout;
import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.BottomOf;
import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.LeftOf;
import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.RightOf;
import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.TopOf;
import Liff.Views.FocusBehaviour;
import Liff.Views.RectView;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.system.CallbackI;
import qiwi.DuplicateConstraintException;
import qiwi.UnsatisfiableConstraintException;


public class MyRender implements Renderable {

    private ConstraintLayout layout;


    @Override
    public void init() {
        Renderer.addShader("UiShader", "src/main/resources/shaders/default.glsl");
        Renderer.addCamera("UiCamera", new Vector2f());

        Vector4f color = new Vector4f(1.0f, 1.0f, 0.0f, 1.0f);
        Vector4f color1 = new Vector4f(1.0f, 0.0f, 0.3f, 1.0f);
        Vector4f color2 = new Vector4f(1.0f, 0.5f, 0.3f, 1.0f);



        //Renderer.submit(new RectShape(100.0f, 100.0f, 200.0f, 200.0f, color), Renderer.getShader("UiShader"), Renderer.getCamera("UiCamera"));
        //Renderer.submit(new RectShape(200.0f, 200.0f, 500.0f, 500.0f, color1), Renderer.getShader("UiShader"), Renderer.getCamera("UiCamera"));
        //Renderer.submit(new RectShape(300.0f, 300.0f, 500.0f, 500.0f, color2), Renderer.getShader("UiShader"), Renderer.getCamera("UiCamera"));

        layout = new ConstraintLayout("root", 740, 620);
        RectView v1 = new RectView("v1");
        RectView v2 = new RectView("v2");
        RectView v3 = new RectView("v3");
        v1.addBehaviours(new FocusBehaviour(v1));
        v2.addBehaviours(new FocusBehaviour(v2));
        layout.addChild(v1);
        layout.addChild(v2);
        layout.addChild(v3);

        layout.setRightConstraint(v1, new LeftOf(v2));
        layout.setLeftConstraint(v1, new LeftOf(layout));
        layout.setBottomConstraint(v1, new BottomOf(layout));
        layout.setTopConstraint(v1, new TopOf(layout));

        //layout.setRightConstraint(v2, new RightOf(layout));
        layout.setLeftConstraint(v2, new RightOf(v1));
        layout.setBottomConstraint(v2, new BottomOf(layout));
        layout.setTopConstraint(v2, new TopOf(layout));

        layout.update(0.0f);

        System.out.println(layout);


        Renderer.processDrawCall();

    }

    @Override
    public void render(float dt) {
        layout.update(dt);

        Renderer.executeDrawCalls();
    }
}
