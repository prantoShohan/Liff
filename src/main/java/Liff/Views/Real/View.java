package Liff.Views.Real;

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
                "rectangle=" + rectangle +
                "}";
    }
}
