package Liff.Components;

import Liff.Entities.Entity;

public abstract class Component {
    protected String name;
    public Entity parent = null;
    public abstract void update(float dt);
    public void init(){}
}
