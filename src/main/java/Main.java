import Liff.Application;
import Liff.Renderable;
import Liff.Renderer.*;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//        100.5f,   0.0f,     0.0f,
//        0.0f,     100.0f,   0.0f,
//        100.0f,   100.0f,   0.0f,
//        0.0f,     0.0f,     0.0f,
public class Main {


    public static void main(String[] args){
//
//        Vertex v1 = new Vertex(100.0f, 0.0f, 0.0f);
//        Vertex v2 = new Vertex(0.0f, 100.0f, 0.0f);
//        Vertex v3 = new Vertex(100.0f, 100.0f, 0.0f);
//        Vertex v4 = new Vertex(0.0f, 0.0f, 0.0f);
//        List<Vertex> v = Arrays.asList(v1, v2, v3, v4);
//        List<Integer> i = Arrays.asList(2, 1, 0, 0, 1, 3);
//
//        Drawdata d = new Drawdata(new Shape(v, i, new Vector4f(1.0f, 0.0f, 0.0f, 1.0f)),
//                new Shader("src/main/resources/shaders/default.glsl"),
//                new Camera(new Vector2f()));
//        System.out.println(d.toString());

        Application app = new Application(740, 620, "Liff of a pimaina");
        app.setRenderObject(new MyRender());
        app.run();
    }
}
