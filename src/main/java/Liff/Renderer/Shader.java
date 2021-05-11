package Liff.Renderer;

import org.joml.Matrix4f;
import org.lwjgl.BufferUtils;

import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;

public class Shader {


    private String vertexSource;
    private String fragmentSource;
    private String filepath;

    private int vertexID, fragmentID;
    private int shaderID;

    public Shader(String filepath){
        this.filepath = filepath;
        try{
            String source = new String(Files.readAllBytes(Paths.get(filepath)));
            String[] shaderSources = source.split("(#type)( )+([a-zA-z]+)");
            vertexSource = shaderSources[1];
            fragmentSource = shaderSources[2];
            System.out.println(vertexSource);
            System.out.println(fragmentSource);

        }catch(IOException e){
            e.printStackTrace();
            assert false:"Error reading shader file."+filepath;
        }
    }

    public void compile(){
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID, vertexSource);
        glCompileShader(vertexID);

        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: compilation in vertex shader.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID, fragmentSource);
        glCompileShader(fragmentID);

        success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: compilation in fragment shader.");
            System.out.println(glGetShaderInfoLog(fragmentID, len));
            assert false : "";
        }

        shaderID = glCreateProgram();
        glAttachShader(shaderID, vertexID);
        glAttachShader(shaderID, fragmentID);
        glLinkProgram(shaderID);

        success = glGetProgrami(shaderID, GL_LINK_STATUS);
        if(success == GL_FALSE){
            int len = glGetProgrami(shaderID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: compilation in linking shader.");
            System.out.println(glGetProgramInfoLog(shaderID, len));
            assert false : "";

        }
    }

    public void uploadMatrix4f(String name, Matrix4f mat){
        int location = glGetUniformLocation(shaderID, name);
        FloatBuffer matBuffer = BufferUtils.createFloatBuffer(16);
        mat.get(matBuffer);
        glUniformMatrix4fv(location, false, matBuffer);
    }

    public void uploadTexture(String name, int slot){
        int location = glGetUniformLocation(shaderID, name);
        glUniform1i(location, slot);
    }

    public void bind(Camera camera){
        glUseProgram(shaderID);
        uploadMatrix4f("uProjection", camera.getProjection());
        uploadMatrix4f("uView", camera.getView());
        updateTextureSamplerUniform();
    }

    public void updateTextureSamplerUniform(){
        int loc = glGetUniformLocation(this.shaderID, "texture_samplers");
        int samplers[] = {0, 1, 2, 3, 4, 5, 6, 7};
        glUniform1iv(loc, samplers);
    }

    public void unbind(){
        glUseProgram(0);
    }

}
