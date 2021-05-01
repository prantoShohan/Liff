package Liff.Views;

import Liff.Shapes.RectShape;

public abstract class UiView extends View{
    public UiView(String id) {
        super(id);
    }
    public abstract void update(float dt);
}
