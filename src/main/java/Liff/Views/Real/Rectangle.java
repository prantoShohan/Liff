package Liff.Views.Real;

public class Rectangle {
    public int posX, posY, width, height, sizeX, sizeY;
    public int margin_left = 16, margin_right = 16, margin_bottom = 16, margin_top = 16;

    public Rectangle(int posX, int posY, int width, int height) {
        this.posX = posX;
        this.posY = posY;
        this.width = width;
        this.height = height;
        this.sizeX = posX+width;
        this.sizeY = posY+height;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "posX=" + posX +
                ", sizeX=" + sizeX +
                ", posY=" + posY +
                ", sizeY=" + sizeY +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
