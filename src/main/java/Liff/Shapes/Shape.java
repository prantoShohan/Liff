package Liff.Shapes;

import Liff.Renderer.Vertex;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.List;

public class Shape {
    private List<Vertex> vertices;
    private String textureName;
    private List<Integer> indices;
    private Vector4f color;
    protected Vector2f[] textureCoordinates;


    public Shape(List<Vertex> vertices, List<Integer> indices, Vector4f color){
        this.vertices = vertices;
        this.indices = indices;
        this.color = color;
        this.textureName = "NO_TEXTURE";

    }
    public Shape(List<Vertex> vertices, List<Integer> indices, String textureName){
        this.vertices = vertices;
        this.indices = indices;
        this.color = new Vector4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.textureName = textureName;
    }

    public List<Vertex> getVertices() { return vertices; }
    public List<Integer> getIndices() { return indices; }

    public Vector4f getColor() {
        return color;
    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String name){
        this.textureName = name;
        this.setColor(new Vector4f(1.0f, 1.0f, 1.0f, 1.0f));
    }

    public void setVertices(List<Vertex> vertices) {
        this.vertices = vertices;
    }

    public void setColor(Vector4f color) {
        this.color = color;
    }
}
