package Liff.Shapes;

import Liff.Renderer.Vertex;
import org.joml.Vector4f;

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

    public Vector4f getColor() {
        return color;
    }


}
