package Liff.Renderer;

import org.joml.Vector2f;
import org.joml.Vector4f;
import org.lwjgl.BufferUtils;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shape {
    private List<Vertex> vertices;
    private List<Integer> indices;
    private Vector4f color;


    public Shape(List<Vertex> vertices, List<Integer> indices, Vector4f color){
        this.vertices = vertices;
        this.indices = indices;
        this.color = color;

    }

    public List<Vertex> getVertices() { return vertices; }
    public List<Integer> getIndices() { return indices; }
    public int getVertexSize(){return 7;}

    public Vector4f getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Shape{" +
                "vertices=" + vertices +
                ", indices=" + indices +
                ", color=" + color +
                '}';
    }
    //    public Shape addShape(Shape s){
//        List<Vertex> shapeVertices = s.getVertices();
//        List<Integer> shapeIndices = s.getIndices();
//
//        int lastInd = Collections.max(indices);
//        for(int i : shapeIndices){
//            this.indices.add(i+lastInd);
//        }
//        this.vertices.addAll(shapeVertices);
//
//        return this;
//    }

}
