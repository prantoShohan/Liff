package Liff.Views.Behaviours;

import Liff.Core.MouseListener;
import Liff.Renderer.Renderer;
import Liff.Shapes.Shape;
import Liff.Views.RectView;
import Util.Rectangle;
import org.joml.Vector4f;

public class FocusBehaviour extends Behaviour{
    Rectangle targetRect;
    Shape targetShape;
    Vector4f originalColor;
    private boolean inFocus = false;
    private float focusColorMultiplier = 1.5f;

    public FocusBehaviour(RectView parent){
        super("FOCUS_BEHAVIOUR");
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
                targetShape.setColor(new Vector4f(originalColor.x*focusColorMultiplier, originalColor.y*focusColorMultiplier, originalColor.z*focusColorMultiplier, originalColor.w*focusColorMultiplier));
                inFocus = true;
                Renderer.setShouldRedraw(true);
                System.out.println("inFocus");
            }
        }else{
            if(!collide(MouseListener.getX(), MouseListener.getY())){
                targetShape.setColor(originalColor);
                inFocus = false;
                Renderer.setShouldRedraw(true);
                System.out.println("outFocus");
            }
        }
    }

    public boolean isInFocus() {
        return inFocus;
    }
}
