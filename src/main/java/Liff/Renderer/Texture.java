package Liff.Renderer;

import org.lwjgl.BufferUtils;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.stb.STBImage.stbi_image_free;
import static org.lwjgl.stb.STBImage.stbi_load;

public class Texture {
    private String filepath;
    private int texID;
    private String name;

    public Texture(String filepath, String name){
        this.filepath = filepath;
        this.name = name;

        texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);

        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_REPEAT);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

        IntBuffer width = BufferUtils.createIntBuffer(1);
        IntBuffer height = BufferUtils.createIntBuffer(1);
        IntBuffer channels = BufferUtils.createIntBuffer(1);

        ByteBuffer image = stbi_load(filepath, width, height, channels, 0);

        if(image != null){
            if(channels.get(0) == 4){
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, width.get(0), height.get(0), 0, GL_RGBA, GL_UNSIGNED_BYTE, image);
            }else if(channels.get(0) == 3){
                glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, width.get(0), height.get(0), 0, GL_RGB, GL_UNSIGNED_BYTE, image);
            }else{
                assert false: "unknown channels";
            }

        }else{
            assert false: "Error: texture could not load "+ filepath;
        }

        stbi_image_free(image);

    }

    public Texture(ByteBuffer bitmap, int width, int height, String name){
        this.name = name;
        texID = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texID);

        glTexImage2D(GL_TEXTURE_2D, 0, GL_ALPHA, width, height, 0, GL_ALPHA, GL_UNSIGNED_BYTE, bitmap);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    }

    public void bind(){
        glBindTexture(GL_TEXTURE_2D, texID);

    }
    public void unbind(){
        glBindTexture(GL_TEXTURE_2D, 0);


    }

    public int getTexID() {
        return texID;
    }

    public String getName(){
        return this.name;
    }
}
