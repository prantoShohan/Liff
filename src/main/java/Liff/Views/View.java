package Liff.Views;

import Liff.Views.ConstraintLayout.ConstraintLayout;
import Util.Rectangle;

public class View {
    protected String id;
    protected View parent;
    protected Rectangle rectangle;

    public View(String id){
        this.id = id;
        this.rectangle = new Rectangle(0, 0, 100, 50);
    }

    public void setParent(ConstraintLayout parent) {
        this.parent = parent;
    }

    public View getParent() {
        return parent;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public void setRectangle(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public String toString() {
        return "\nView{" +
                "id=" + id +
                " " + rectangle +
                "}";
    }

    public void updateRectangle(int posx, int posy, int sizex, int sizey, int width, int height){
        this.rectangle.posX = posx;
        this.rectangle.posY = posy;
        this.rectangle.sizeX = sizex;
        this.rectangle.sizeY = sizey;
        this.rectangle.width = width;
        this.rectangle.height = height;
    }
}
