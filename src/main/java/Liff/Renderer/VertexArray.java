package Liff.Renderer;

import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.*;

public class VertexArray {
    private int vaoID, vboID, eboID;

    public VertexArray(FloatBuffer vbo, IntBuffer ibo){
        vaoID = glGenVertexArrays();
        glBindVertexArray(vaoID);

        vboID = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vboID);
        glBufferData(GL_ARRAY_BUFFER, vbo, GL_STATIC_DRAW);

        eboID = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, ibo, GL_STATIC_DRAW);

        int positionSize = 3, colorSize = 4, textureCoordinateSize = 2, textureIdSize = 1;
        int vertexSizeBytes = (positionSize+colorSize+textureCoordinateSize+textureIdSize)*Float.BYTES;

        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, textureCoordinateSize, GL_FLOAT, false, vertexSizeBytes, (positionSize+colorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);

        glVertexAttribPointer(3, textureIdSize, GL_FLOAT, false, vertexSizeBytes, (positionSize+colorSize+textureCoordinateSize) * Float.BYTES);
        glEnableVertexAttribArray(3);

        glBindVertexArray(0);

    }

    public void destroy(){
        glDeleteVertexArrays(vaoID);
    }

    public void bind(){
        glBindVertexArray(vaoID);
    }
}
