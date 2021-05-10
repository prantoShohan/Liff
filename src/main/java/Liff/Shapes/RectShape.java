package Liff.Shapes;

import Liff.Renderer.Vertex;
import Util.Rectangle;
import org.joml.Vector2f;
import org.joml.Vector4f;

import java.util.Arrays;
import java.util.List;

public class RectShape extends Shape{


    public RectShape(float x, float y, float sx, float sy, Vector4f color) {
        super(Arrays.asList(new Vertex(sx, y, 0.0f), new Vertex(x, sy, 0.0f), new Vertex(sx, sy, 0.0f), new Vertex(x, y, 0.0f)), Arrays.asList(2, 1, 0, 0, 1, 3), color);
        this.textureCoordinates = new Vector2f[]{new Vector2f(0, 0),
                                                 new Vector2f(0, 0),
                                                 new Vector2f(0, 0),
                                                 new Vector2f(0, 0),};

    }


    public RectShape(float sx, float sy, String textureName, Vector2f[] textureCoordinates){
        super(Arrays.asList(new Vertex(sx,  0,  0.0f, textureCoordinates[3].x, textureCoordinates[3].y),
                            new Vertex(0,  sy,  0.0f, textureCoordinates[0].x, textureCoordinates[0].y),
                            new Vertex(sx,    sy,  0.0f, textureCoordinates[1].x, textureCoordinates[1].y),
                            new Vertex(0, 0, 0.0f, textureCoordinates[2].x, textureCoordinates[2].y)),
                            Arrays.asList(2, 1, 0, 0, 1, 3), textureName);
        this.textureCoordinates = textureCoordinates;

    }
    public void updateRectangle(Rectangle rect){
        setVertices(Arrays.asList(new Vertex(rect.sizeX, rect.posY,  0.0f, this.textureCoordinates[3].x, this.textureCoordinates[3].y),
                                  new Vertex(rect.posX,  rect.sizeY, 0.0f, this.textureCoordinates[0].x, this.textureCoordinates[0].y),
                                  new Vertex(rect.sizeX, rect.sizeY, 0.0f, this.textureCoordinates[1].x, this.textureCoordinates[1].y),
                                  new Vertex(rect.posX,  rect.posY,  0.0f, this.textureCoordinates[2].x, this.textureCoordinates[2].y)));

    }
}
