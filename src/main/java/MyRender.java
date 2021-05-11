import Liff.Core.Renderable;
import Liff.Renderer.*;
import Util.Command;
import Liff.Views.ConstraintLayout.ConstraintLayout;
import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.BottomOf;
import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.LeftOf;
import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.RightOf;
import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.TopOf;
import Liff.Views.Behaviours.FocusBehaviour;
import Liff.Views.Behaviours.OnClickBehaviour;
import Liff.Views.RectView;
import Util.IOUtil;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTAlignedQuad;
import org.lwjgl.stb.STBTTPackContext;
import org.lwjgl.stb.STBTTPackedchar;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBTruetype.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;


public class MyRender implements Renderable {

    private ConstraintLayout layout;
    private Text text;

    @Override
    public void init() {
        Renderer.addShader("UiShader", "src/main/resources/shaders/default.glsl");
        Renderer.addCamera("UiCamera", new Vector2f());
        Renderer.addTexture("NEW_TEXTURE", "src/main/resources/textures/logo.png");
        Renderer.addTexture("Superman", "src/main/resources/textures/Superman.png");

        Font font = new Font("D:/Desktop/FiraSansR.ttf", "FiraSans");
        Text text = new Text(font, "Hello Timoni :\"))", new Vector2f(100, 100), new Vector4f(1, 0, 0, 1));
        text.UpdateQuads();
        text.submit();

        layout = new ConstraintLayout("root", 740, 620);
        RectView v1 = new RectView("v1");
        RectView v2 = new RectView("v2");
        RectView v3 = new RectView("v3");
        RectView image = new RectView("image","NEW_TEXTURE", 100, 100);
        RectView image2 = new RectView("im2", "Superman", 200, 200);



        v1.addBehaviours(new FocusBehaviour(v1));
        v2.addBehaviours(new FocusBehaviour(v2));
        v1.addBehaviours(new OnClickBehaviour(v1, new Command(){
            @Override
            public void execute(float dt) {
                System.out.println("Clicked");
            }
        }));

        layout.addChild(v1);
        layout.addChild(v2);
        layout.addChild(v3);
        layout.addChild(image);
        layout.addChild(image2);

        layout.setRightConstraint(v1, new RightOf(layout));
        layout.setLeftConstraint(v1, new LeftOf(layout));
        layout.setBottomConstraint(v1, new BottomOf(layout));
        layout.setTopConstraint(v1, new TopOf(layout));

        layout.setRightConstraint( image, new RightOf(layout));
        layout.setLeftConstraint(  image, new LeftOf(layout));
        layout.setBottomConstraint(image, new TopOf(v1));
        layout.setTopConstraint(   image, new TopOf(layout));

        layout.setRightConstraint( image2, new RightOf(layout));
        layout.setLeftConstraint(  image2, new LeftOf(layout));
        layout.setBottomConstraint(image2, new BottomOf(layout));
        layout.setTopConstraint(   image2, new BottomOf(v1));

        layout.setRightConstraint(v2, new RightOf(layout));
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
