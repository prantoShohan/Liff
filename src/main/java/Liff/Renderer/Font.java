package Liff.Renderer;

import Util.IOUtil;
import org.lwjgl.BufferUtils;
import org.lwjgl.stb.STBTTPackContext;
import org.lwjgl.stb.STBTTPackedchar;
import org.lwjgl.system.MemoryUtil;

import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.stb.STBTruetype.*;

public class Font {
    private STBTTPackedchar.Buffer charData;
    private STBTTPackContext packContext;
    private ByteBuffer ttf;
    private ByteBuffer bitmap;
    private Texture fontTexture;
    private String name;
    private int width = 512, height = 512;
    private int fontSize = 30;

    public Font(String path, String name){
        this.name = name;
        charData = STBTTPackedchar.malloc(256);
        packContext = STBTTPackContext.malloc();
        try {
            ttf = IOUtil.ioResourceToByteBuffer(path, 512*1024);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //TODO create this as variable
        bitmap = BufferUtils.createByteBuffer(width * width);
        stbtt_PackBegin(packContext, bitmap, width, height, 0, 1, MemoryUtil.NULL);
        charData.limit(128);
        charData.position(32);
        stbtt_PackSetOversampling(packContext, 3, 3);
        //TODO Font size feature
        stbtt_PackFontRange(packContext, ttf, 0, fontSize, 32, charData);
        charData.clear();
        stbtt_PackEnd(packContext);

        this.fontTexture = new Texture(bitmap, width, height, this.name+"Texture");
        Renderer.addTexture(this.fontTexture, this.name+"Texture");
    }

    public STBTTPackedchar.Buffer getCharData() {
        return charData;
    }
    public String getTextureName(){
        return this.fontTexture.getName();
    }
}
