package Liff.Renderer;

import Liff.Shapes.RectShape;
import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.stb.STBTTAlignedQuad;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.stb.STBTruetype.stbtt_GetPackedQuad;
import static org.lwjgl.system.MemoryUtil.memAllocFloat;

public class Text {
    private Font font;
    private String text;
    private Vector4f color;
    private List<RectShape> quads = new ArrayList<>();
    private FloatBuffer positionXFloatBuffer = memAllocFloat(1);
    private FloatBuffer positionYFloatBuffer = memAllocFloat(1);
    private STBTTAlignedQuad alignedQuad = STBTTAlignedQuad.malloc();
    private Vector2f position;

    public Text(Font font, String text, Vector2f position, Vector4f color){
        this.font = font;
        this.text = text;
        this.color = color;
        this.position = position;
    }
    public void UpdateQuads(){
        this.positionXFloatBuffer.put(0, this.position.x);
        this.positionYFloatBuffer.put(0, this.position.y);

        this.font.getCharData().position(0);
        for(int i = 0; i<this.text.length(); i++){
            stbtt_GetPackedQuad(this.font.getCharData(), 512, 512, this.text.charAt(i), this.positionXFloatBuffer, positionYFloatBuffer, alignedQuad, true);
            quads.add(new RectShape(alignedQuad, this.font.getTextureName(), (int)this.position.y));
        }
    }

    public void submit(){
        for(RectShape r: quads){
            Renderer.submit(r, Renderer.getShader("UiShader"), Renderer.getCamera("UiCamera"));
        }
    }
}
