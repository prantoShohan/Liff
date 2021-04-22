package Liff.Renderer;

import org.lwjgl.BufferUtils;

import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.lwjgl.opengl.GL11.*;

public class DrawCall {
    private Shader shader;
    private Camera camera;
    private FloatBuffer vertexBuffer;
    private IntBuffer indexBuffer;
    private List<DrawData> drawDataList;


    public DrawCall(Shader shader, Camera camera) {
        this.shader = shader;
        this.camera = camera;
        drawDataList = new ArrayList<>();

    }


    public void processDrawData(){
        List<Float> vertexBufferList = new ArrayList<>();
        List<Integer> indexBufferList = new ArrayList<>();
        int currentIndex = 0;
        for(DrawData d: drawDataList){
            for(Vertex v: d.shape.getVertices()){
                vertexBufferList.add(v.x);
                vertexBufferList.add(v.y);
                vertexBufferList.add(v.z);
                vertexBufferList.add(d.shape.getColor().x);
                vertexBufferList.add(d.shape.getColor().y);
                vertexBufferList.add(d.shape.getColor().z);
                vertexBufferList.add(d.shape.getColor().w);
            }
            for(int i: d.shape.getIndices()){
                indexBufferList.add(i+currentIndex);
            }
            currentIndex = Collections.max(indexBufferList);
        }
        vertexBuffer = BufferUtils.createFloatBuffer(vertexBufferList.size());
        indexBuffer = BufferUtils.createIntBuffer(indexBufferList.size());

        //TODO optimize this
        for(Float f: vertexBufferList){
            vertexBuffer.put(f);
        }
        for(int i:indexBufferList){
            indexBuffer.put(i);
        }
        vertexBuffer.flip();
        indexBuffer.flip();
    }

    public void render(){
        VertexArray vao = new VertexArray(vertexBuffer, indexBuffer);
        vao.bind();
        shader.bind(camera);
        drawcall();
        shader.unbind();
        vao.destroy();
    }

    public boolean isSameAs(DrawData d){
        if (this.camera == d.camera && this.shader == d.shader){
            return true;
        }else return false;
    }
    public void addDrawData(DrawData d){
        this.drawDataList.add(d);
    }

    public void drawcall(){
        glDrawElements(GL_TRIANGLES, indexBuffer.capacity(), GL_UNSIGNED_INT, 0);
    }

}
