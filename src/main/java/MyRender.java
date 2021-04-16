import Liff.Renderable;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class MyRender implements Renderable {

    private String vertexShaderSrc = "#version 330 core\n" +
            "layout(location = 0)in vec3 aPos;\n" +
            "layout(location = 1)in vec4 aColor;\n" +
            "\n" +
            "out vec4 fColor;\n" +
            "\n" +
            "void main(){\n" +
            "    fColor = aColor;\n" +
            "    gl_Position = vec4(aPos, 1.0);\n" +
            "}";
    private String fragmentShaderSrc = "#version 330 core\n" +
            "in vec4 fColor;\n" +
            "out vec4 color;\n" +
            "\n" +
            "void main(){\n" +
            "    color = fColor;\n" +
            "}";

    private int vertexID, fragmentID, shaderID;
    private int vaoID, vboID, eboID;

    private float[] vertexArray = {
             0.5f, -0.5f,   0.0f,              1.0f, 0.0f, 0.0f, 1.0f,
            -0.5f,   0.5f,   0.0f,              1.0f, 0.0f, 0.0f, 1.0f,
             0.5f,   0.5f,   0.0f,              1.0f, 0.0f, 0.0f, 1.0f,
            -0.5f,  -0.5f,   0.0f,              1.0f, 0.0f, 0.0f, 1.0f,

    };
    private int[] elementArray = {
            2, 1, 0,
            0, 1, 3
    };

    @Override
    public void init() {
        vertexID = glCreateShader(GL_VERTEX_SHADER);
        glShaderSource(vertexID, vertexShaderSrc);
        glCompileShader(vertexID);

        int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
        if(success == GL_FALSE){
            int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
            System.out.println("ERROR: compilation in vertex shader.");
            System.out.println(glGetShaderInfoLog(vertexID, len));
            assert false : "";
        }

        fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
        glShaderSource(fragmentID, fragmentShaderSrc);
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

        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
        vertexBuffer.put(vertexArray).flip();

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

        IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
        elementBuffer.put(elementArray).flip();

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

        int positionSize = 3, colorSize = 4, floatSizeBytes = 4;
        int vertexSizeBytes = (positionSize+colorSize)*floatSizeBytes;

        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * floatSizeBytes);
        glEnableVertexAttribArray(1);

    }

    @Override
    public void render(float dt) {
        //System.out.println(1/dt);
        glUseProgram(shaderID);
        glBindVertexArray(vaoID);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);



    }
}
