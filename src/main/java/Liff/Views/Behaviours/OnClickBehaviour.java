package Liff.Views.Behaviours;

import Liff.Core.MouseListener;
import Liff.Views.RectView;
import Util.Command;

import static org.lwjgl.glfw.GLFW.GLFW_MOUSE_BUTTON_LEFT;

public class OnClickBehaviour extends Behaviour{
    private Command onClick;
    private FocusBehaviour supportive;
    private boolean clicked = false;

    public OnClickBehaviour(RectView parent, Command onClick) {
        super("ONCLICK_BEHAVIOUR");
        this.onClick = onClick;
        setParent(parent);
        if(this.parent.doesBehave("FOCUS_BEHAVIOUR")){
            this.supportive = (FocusBehaviour) this.parent.getBehaviour("FOCUS_BEHAVIOUR");
        }else{
            //TODO ctrate passive focus behaviour
            this.parent.addBehaviours(new FocusBehaviour(this.parent));
        }
    }

    @Override
    public void update(float dt) {
        //TODO fix clicking if clicked from outside of button and dragged to the focus
        if(supportive.isInFocus()){
            if(!clicked) {
                if (MouseListener.getButtonDown(GLFW_MOUSE_BUTTON_LEFT)) {
                    clicked = true;
                    this.onClick.execute(dt);
                }
            }else if(!MouseListener.getButtonDown(GLFW_MOUSE_BUTTON_LEFT)){
                clicked = false;
            }
        }else{
            clicked = false;
        }
    }
}
