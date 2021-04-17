import Liff.Renderable;
import Liff.Renderer.Camera;
import Liff.Renderer.Shader;
import Liff.Renderer.Texture;
import org.joml.Vector2f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class MyRender implements Renderable {

    private int vaoID, vboID, eboID;
    private Shader shader;
    private Camera camera;
    private Texture texture;

    private float[] vertexArray = {
             100.5f,   0.0f,     0.0f,              1.0f, 0.0f, 0.0f, 1.0f,         1, 1,
             0.0f,     100.0f,   0.0f,              1.0f, 0.0f, 0.0f, 1.0f,         0, 0,
             100.0f,   100.0f,   0.0f,              1.0f, 0.0f, 0.0f, 1.0f,         1, 0,
             0.0f,     0.0f,     0.0f,              1.0f, 0.0f, 0.0f, 1.0f,         0, 1

    };
    private int[] elementArray = {
            2, 1, 0,
            0, 1, 3
    };

    @Override
    public void init() {

        shader = new Shader("src/main/resources/shaders/default.glsl");
        camera = new Camera(new Vector2f());
        shader.compile();
        this.texture = new Texture("src/main/resources/textures/logo.png");



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

        int positionSize = 3, colorSize = 4, uvSize = 2;
        int vertexSizeBytes = (positionSize+colorSize+uvSize)*Float.BYTES;

        glVertexAttribPointer(0, positionSize, GL_FLOAT, false, vertexSizeBytes, 0);
        glEnableVertexAttribArray(0);

        glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeBytes, positionSize * Float.BYTES);
        glEnableVertexAttribArray(1);

        glVertexAttribPointer(2, uvSize, GL_FLOAT, false, vertexSizeBytes, (positionSize+colorSize) * Float.BYTES);
        glEnableVertexAttribArray(2);



    }

    @Override
    public void render(float dt) {
        //System.out.println(1/dt);
        camera.position.x -= .05/dt;
        shader.bind();
        shader.uploadMatrix4f("uProjection", camera.getProjection());
        shader.uploadMatrix4f("uView", camera.getView());

        shader.uploadTexture("texture_sampler", 0);
        glActiveTexture(GL_TEXTURE0);
        texture.bind();

        glBindVertexArray(vaoID);

        glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);



    }
}
