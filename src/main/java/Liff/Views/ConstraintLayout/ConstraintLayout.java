package Liff.Views.ConstraintLayout;

import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.Constraint;
import Liff.Views.UiView;
import Util.Rectangle;
import Liff.Views.View;
import qiwi.DuplicateConstraintException;
import qiwi.Solver;
import qiwi.Symbolics;
import qiwi.UnsatisfiableConstraintException;

import java.util.Hashtable;
import java.util.Set;

public class ConstraintLayout extends UiView {
    private enum Mode {CHILD_MODE, ROOT_MODE};

    private Solver constraintSolver = new Solver();
    private Hashtable<UiView, ConstraintWidget> children = new Hashtable<>();
    private ConstraintWidget constraintWidget;
    private Mode mode;

    public ConstraintLayout(String id) {
        super("Constraint_Layout_"+id);
        this.mode = Mode.CHILD_MODE;
        this.constraintWidget = new ConstraintWidget(this, null);
    }



    public ConstraintLayout(String id, int sizeX, int sizeY) {
        super("Constraint_Layout_"+id);
        this.setRectangle(new Rectangle(0, 0, sizeX, sizeY));
        this.setParent(null);
        this.mode = Mode.ROOT_MODE;
        this.constraintWidget = new ConstraintWidget(this, null);
    }

    @Override
    public void update(float dt) {
        //TODO it is doing everything everytime
        try {
            this.updateConstraints();
        } catch (UnsatisfiableConstraintException | DuplicateConstraintException e) {
            e.printStackTrace();
        }
        this.updateChildrenVariables();
        Set<UiView> viewSet = children.keySet();
        for(UiView v:viewSet){
            v.update(dt);
        }
    }

    public void updateChildren(){
        Set<UiView> viewSet = children.keySet();
        for(UiView v:viewSet){
            v.update(0.0f);
        }
    }

    @Override
    public void setParent(ConstraintLayout parent) {
        super.setParent(parent);
        if(parent!=null){
            this.mode = Mode.CHILD_MODE;
        }
    }

    public void addChild(UiView v){
        v.setParent(this);
        this.children.put(v, new ConstraintWidget(v,this));
    }

    public void updateConstraints() throws UnsatisfiableConstraintException, DuplicateConstraintException {
        if(this.mode == Mode.ROOT_MODE){
            constraintSolver.addConstraint(Symbolics.equals(this.constraintWidget.getVariable_left(), this.rectangle.posX));
            constraintSolver.addConstraint(Symbolics.equals(this.constraintWidget.getVariable_right(), this.rectangle.width));
            constraintSolver.addConstraint(Symbolics.equals(this.constraintWidget.getVariable_bottom(), this.rectangle.posY));
            constraintSolver.addConstraint(Symbolics.equals(this.constraintWidget.getVariable_top(), this.rectangle.height));
            Set<UiView> viewSet = children.keySet();
            for(UiView v:viewSet){
                children.get(v).updateBehaviours();
                children.get(v).addConstraints(constraintSolver);
            }
        }//TODO what if a constraint layout is added as a child of another constrataint layout?
    }

    public void updateChildrenVariables(){
        constraintSolver.updateVariables();
        Set<UiView> viewSet = children.keySet();
        for(UiView v:viewSet){
            children.get(v).updateTargetRectangle();
        }
    }

    public ConstraintWidget getConstraintWidget() {
        return constraintWidget;
    }

    public void setLeftConstraint(UiView v, Constraint c){
        children.get(v).setLeftConstraint(c);
    }
    public void setRightConstraint(UiView v, Constraint c){
        children.get(v).setRightConstraint(c);
    }
    public void setBottomConstraint(UiView v, Constraint c){
        children.get(v).setBottomConstraint(c);
    }
    public void setTopConstraint(UiView v, Constraint c){
        children.get(v).setTopConstraint(c);
    }

    public void setWidthConstraint(UiView v, Constraint c){
        children.get(v).setBottomConstraint(c);
    }
    public void setHeightConstraint(UiView v, Constraint c){
        children.get(v).setTopConstraint(c);
    }

    public void setHorizontalConstraint(UiView v, Constraint c){

    }

    public ConstraintWidget getCorrespondingWidget(UiView v){
        if(v == this){
            return constraintWidget;
        }else{
            return children.get(v);
        }
    }

    @Override
    public String toString() {
        return "ConstraintLayout{" +
                "children=" + children +
                '}';
    }
}
