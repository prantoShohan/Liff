package Liff.Shapes;

import Liff.Renderer.Vertex;
import org.joml.Vector4f;

import java.util.Arrays;
import java.util.List;

public class RectShape extends Shape{
    public RectShape(float x, float y, float sx, float sy, Vector4f color) {
        super(Arrays.asList(new Vertex(sx, y, 0.0f), new Vertex(x, sy, 0.0f), new Vertex(sx, sy, 0.0f), new Vertex(x, y, 0.0f)), Arrays.asList(2, 1, 0, 0, 1, 3), color);
    }
}
