package Liff.Views.Behaviours;

import Liff.Views.RectView;

public abstract class Behaviour {
    protected RectView parent;
    public abstract void update(float dt);
    protected String id;

    public Behaviour(String id) {
        this.id = id;
    }

    public String getId(){
        return id;
    }
    public void setParent(RectView parent) {
        this.parent = parent;
    }
}
