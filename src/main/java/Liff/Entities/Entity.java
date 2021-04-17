package Liff.Entities;

import Liff.Components.Component;

import java.util.ArrayList;
import java.util.List;

public class Entity {
    public String name;
    private List<Component> components;

    public Entity(String name){
        this.name = name;
        components = new ArrayList<>();
    }

    public <T extends Component> T getComponent(Class<T> componentClass){
        for (Component c : components){
            if(componentClass.isAssignableFrom(c.getClass())){
                try {
                    return componentClass.cast(c);
                }catch(ClassCastException e){
                    e.printStackTrace();
                    assert false:"error casting component class";
                }
            }

        }
        return null;
    }

    public <T extends Component> void removeComponent(Class<T> componentClass){
        for(int i = 0; i < components.size(); i++){
            Component c = components.get(i);
            if(componentClass.isAssignableFrom(c.getClass())){
                components.remove(i);
                return;
            }
        }
    }

    public void addComponent(Component c){
        this.components.add(c);
        c.parent = this;
        //c.init();
    }

    public void update(float dt){
        for(int i = 0; i < components.size(); i++){
            components.get(i).update(dt);
        }
    }

    //TODO: it also inits on the add component; fix this
    public void init(){
        for(int i = 0; i < components.size(); i++){
            components.get(i).init();
        }
    }
}
