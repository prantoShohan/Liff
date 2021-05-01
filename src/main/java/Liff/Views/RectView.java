package Liff.Views;

import Liff.Renderer.Renderer;
import Liff.Shapes.RectShape;
import org.joml.Vector4f;

public class RectView extends UiView{
    private RectShape shape;

    public RectView(String id) {
        super(id);
        shape = new RectShape(0, 0, 100, 50, new Vector4f(0.4f, 0.4f, 0.4f, 1.0f));
    }

    public void setDefaultSize(int x, int y){
        this.rectangle.width = x;
        this.rectangle.height = y;
        shape = new RectShape(this.rectangle.posX, this.rectangle.posY, this.rectangle.sizeX, this.rectangle.sizeY,  new Vector4f(0.4f, 0.4f, 0.4f, 1.0f));
    }

    @Override
    public void updateRectangle(int posx, int posy, int sizex, int sizey, int width, int height) {
        super.updateRectangle(posx, posy, sizex, sizey, width, height);
        shape.updateRectangle(this.rectangle);
    }

    @Override
    public void update(float dt) {
        Renderer.submit(shape, Renderer.getShader("UiShader"), Renderer.getCamera("UiCamera"));
    }
}
