package Liff.Views;

public abstract class Behaviour {
    protected RectView parent;
    public abstract void update(float dt);

    public void setParent(RectView parent) {
        this.parent = parent;
    }
}
