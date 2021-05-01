package Liff.Views.Real;

import Liff.Views.Real.Constraints.Constraint;
import qiwi.DuplicateConstraintException;
import qiwi.Solver;
import qiwi.Symbolics;
import qiwi.UnsatisfiableConstraintException;

import java.util.Hashtable;
import java.util.Set;

public class ConstraintLayout extends View{
    private enum Mode {CHILD_MODE, ROOT_MODE};

    private Solver constraintSolver = new Solver();
    private Hashtable<View, ConstraintWidget> children = new Hashtable<>();
    private ConstraintWidget constraintWidget;
    private Mode mode;

    public ConstraintLayout(String id) {
        super("Constraint_Layout_"+id);
        this.mode = Mode.CHILD_MODE;
        this.constraintWidget = new ConstraintWidget(this.rectangle, null);
    }

    public ConstraintLayout(String id, int sizeX, int sizeY) {
        super("Constraint_Layout_"+id);
        this.setRectangle(new Rectangle(0, 0, sizeX, sizeY));
        this.setParent(null);
        this.mode = Mode.ROOT_MODE;
        this.constraintWidget = new ConstraintWidget(this.rectangle, null);
    }

    @Override
    public void setParent(ConstraintLayout parent) {
        super.setParent(parent);
        if(parent!=null){
            this.mode = Mode.CHILD_MODE;
        }
    }

    public void addChild(View v){
        v.setParent(this);
        this.children.put(v, new ConstraintWidget(v.getRectangle(),this));
    }

    public void updateConstraints() throws UnsatisfiableConstraintException, DuplicateConstraintException {
        if(this.mode == Mode.ROOT_MODE){
            constraintSolver.addConstraint(Symbolics.equals(this.constraintWidget.getVariable_left(), this.rectangle.posX));
            constraintSolver.addConstraint(Symbolics.equals(this.constraintWidget.getVariable_right(), this.rectangle.width));
            constraintSolver.addConstraint(Symbolics.equals(this.constraintWidget.getVariable_bottom(), this.rectangle.posY));
            constraintSolver.addConstraint(Symbolics.equals(this.constraintWidget.getVariable_top(), this.rectangle.height));
            Set<View> viewSet = children.keySet();
            for(View v:viewSet){
                children.get(v).updateBehaviours();
                children.get(v).addConstraints(constraintSolver);
            }
        }//TODO what if a constraint layout is added as a child of another constrataint layout?
    }

    public void updateChildrenVariables(){
        constraintSolver.updateVariables();
        Set<View> viewSet = children.keySet();
        for(View v:viewSet){
            children.get(v).updateTargetRectangle();
        }
    }

    public ConstraintWidget getConstraintWidget() {
        return constraintWidget;
    }

    public void setLeftConstraint(View v, Constraint c){
        children.get(v).setLeftConstraint(c);
    }
    public void setRightConstraint(View v, Constraint c){
        children.get(v).setRightConstraint(c);
    }
    public void setBottomConstraint(View v, Constraint c){
        children.get(v).setBottomConstraint(c);
    }
    public void setTopConstraint(View v, Constraint c){
        children.get(v).setTopConstraint(c);
    }

    public void setWidthConstraint(View v, Constraint c){
        children.get(v).setBottomConstraint(c);
    }
    public void setHeightConstraint(View v, Constraint c){
        children.get(v).setTopConstraint(c);
    }

    public void setHorizontalConstraint(View v, Constraint c){

    }

    public ConstraintWidget getCorrespondingWidget(View v){
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
