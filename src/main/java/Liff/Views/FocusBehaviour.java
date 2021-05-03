package Liff.Views;

import Liff.MouseListener;
import Liff.Renderer.Renderer;
import Liff.Shapes.RectShape;
import Liff.Shapes.Shape;
import Util.Rectangle;
import org.joml.Vector4f;

public class FocusBehaviour extends Behaviour{
    Rectangle targetRect;
    Shape targetShape;
    Vector4f originalColor;
    private boolean inFocus = false;

    public FocusBehaviour(RectView parent) {
        this.setParent(parent);
        this.targetRect = this.parent.getRectangle();
        this.targetShape = this.parent.getShape();
        this.originalColor = targetShape.getColor();
    }

    private boolean collide(float x, float y){
        if(x>targetRect.posX&&y>targetRect.posY&&y<targetRect.sizeY&&x<targetRect.sizeX){
            return true;
        }else{
            return false;
        }
    }
    @Override
    public void update(float dt) {
        if (!inFocus) {
            if(collide(MouseListener.getX(), MouseListener.getY())){
                targetShape.setColor(new Vector4f(0.4f, 0.4f, 0.4f, 1.0f));
                inFocus = true;
                Renderer.setShouldRedraw(true);
                System.out.println("inFocus");
            }
        }else{
            if(!collide(MouseListener.getX(), MouseListener.getY())){
                targetShape.setColor(originalColor);
                inFocus = false;
                Renderer.setShouldRedraw(true);;
            }
        }
    }
}
