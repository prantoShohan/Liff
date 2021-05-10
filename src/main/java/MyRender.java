import Liff.Renderable;
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
    private int font_tex;
    private STBTTPackedchar.Buffer chardata;
    private static final int BITMAP_W = 512;
    private static final int BITMAP_H = 512;
    private int fontSize = 20;
    private final FloatBuffer xb = memAllocFloat(1);
    private final FloatBuffer      yb = memAllocFloat(1);
    private final STBTTAlignedQuad q  = STBTTAlignedQuad.malloc();
    private boolean integer_align = true ;


    private void load_fonts() {
        font_tex = glGenTextures();
        chardata = STBTTPackedchar.malloc(6 * 128);

        try (STBTTPackContext pc = STBTTPackContext.malloc()) {
            ByteBuffer ttf = IOUtil.ioResourceToByteBuffer("D:/Desktop/FiraSansR.ttf", 512 * 1024);

            ByteBuffer bitmap = BufferUtils.createByteBuffer(BITMAP_W * BITMAP_H);

            stbtt_PackBegin(pc, bitmap, BITMAP_W, BITMAP_H, 0, 1, NULL);

            int p = (0 * 3 + 2) * 128 + 32;
            chardata.limit(p + 95);
            chardata.position(p);
            stbtt_PackSetOversampling(pc, 3, 1);
            stbtt_PackFontRange(pc, ttf, 0, fontSize, 32, chardata);

            chardata.clear();
            stbtt_PackEnd(pc);

            glBindTexture(GL_TEXTURE_2D, font_tex);
            glTexImage2D(GL_TEXTURE_2D, 0, GL_ALPHA, BITMAP_W, BITMAP_H, 0, GL_ALPHA, GL_UNSIGNED_BYTE, bitmap);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
            glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void drawBoxTC(float x0, float y0, float x1, float y1, float s0, float t0, float s1, float t1) {
        glTexCoord2f(s0, t0);
        glVertex2f(x0, y0);
        glTexCoord2f(s1, t0);
        glVertex2f(x1, y0);
        glTexCoord2f(s1, t1);
        glVertex2f(x1, y1);
        glTexCoord2f(s0, t1);
        glVertex2f(x0, y1);
    }

    private void print(float x, float y, int font, String text) {
        xb.put(0, x);
        yb.put(0, y);

        chardata.position(font * 128);

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, font_tex);

        glBegin(GL_QUADS);
        for (int i = 0; i < text.length(); i++) {
            stbtt_GetPackedQuad(chardata, BITMAP_W, BITMAP_H, text.charAt(i), xb, yb, q, font == 0 && integer_align);
            drawBoxTC(
                    q.x0(), q.y0(), q.x1(), q.y1(),
                    q.s0(), q.t0(), q.s1(), q.t1()
            );
        }
        glEnd();
    }

    @Override
    public void init() {
        this.load_fonts();
        Renderer.addShader("UiShader", "src/main/resources/shaders/default.glsl");
        Renderer.addCamera("UiCamera", new Vector2f());
        Renderer.addTexture("NEW_TEXTURE", "src/main/resources/textures/logo.png");
        Renderer.addTexture("Superman", "src/main/resources/textures/Superman.png");

        Vector4f color = new Vector4f(1.0f, 1.0f, 0.0f, 1.0f);
        Vector4f color1 = new Vector4f(1.0f, 0.0f, 0.3f, 1.0f);
        Vector4f color2 = new Vector4f(1.0f, 0.5f, 0.3f, 1.0f);

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
        //print(100, 100, 3, "This is a test");

    }

    @Override
    public void render(float dt) {
        //print(200, 100, 3, "This is a test");
        layout.update(dt);

        Renderer.executeDrawCalls();
    }
}
