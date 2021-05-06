package Liff.Views;

import Liff.Renderer.Renderer;
import Liff.Shapes.RectShape;
import Liff.Shapes.Shape;
import Liff.Views.Behaviours.Behaviour;
import org.joml.Vector4f;

import java.util.Hashtable;
import java.util.Set;

public class RectView extends UiView{
    private RectShape shape;
    private Hashtable<String, Behaviour> behaviours = new Hashtable<>();

    public RectView(String id) {
        super(id);
        //TODO the bias doesnt work
        shape = new RectShape(0, 0, 100, 50, new Vector4f(0.3f, 0.3f, 0.3f, 1.0f));
    }

    public void setDefaultSize(int x, int y){
        this.rectangle.width = x;
        this.rectangle.height = y;
        shape = new RectShape(this.rectangle.posX, this.rectangle.posY, this.rectangle.sizeX, this.rectangle.sizeY, new Vector4f(0.3f, 0.3f, 0.3f, 1.0f));
    }

    @Override
    public void updateRectangle(int posx, int posy, int sizex, int sizey, int width, int height) {
        super.updateRectangle(posx, posy, sizex, sizey, width, height);
        shape.updateRectangle(this.rectangle);
    }

    @Override
    public void update(float dt) {
        if(Renderer.isShouldRedraw()) {
            Renderer.submit(shape, Renderer.getShader("UiShader"), Renderer.getCamera("UiCamera"));
        }
        Set<String> behaviourId = behaviours.keySet();
        for(String b: behaviourId){
            behaviours.get(b).update(dt);
            //System.out.println("updating behaviours");
        }
    }

    public void addBehaviours(Behaviour b){
        behaviours.put(b.getId(), b);
    }

    public boolean doesBehave(String behaviourId){
        if (behaviours.containsKey(behaviourId)){
            return true;
        }else{
            return false;
        }
    }

    public Behaviour getBehaviour(String behaviourId){
        if(this.doesBehave(behaviourId)){
            return behaviours.get(behaviourId);
        }
        else return null;
    }


    public Shape getShape(){
        return this.shape;
    }
}
