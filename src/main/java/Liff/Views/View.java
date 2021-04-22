package Liff.Views;

public class View {

    private String id;
    private Bound bound;
    private ConstraintLayout parent;

    private LayoutParameter layoutParam;

    private void setLeftConstraint(Constraint c){
        //TODO same parent safety
        layoutParam.setLeftConstraint(c);
        parent.addChildConstraint(c);
    }
    private void setRightConstraint(Constraint c){
        layoutParam.setRightConstraint(c);
        parent.addChildConstraint(c);
    }
    private void setTopConstraint(Constraint c){
        layoutParam.setTopConstraint(c);
        parent.addChildConstraint(c);
    }
    private void setBottomConstraint(Constraint c){
        layoutParam.setBottomConstraint(c);
        parent.addChildConstraint(c);
    }
    private void setCenterConstraintX(Constraint c){
        layoutParam.setCenterConstraintX(c);
        parent.addChildConstraint(c);
    }
    private void setCenterConstraintY(Constraint c){
        layoutParam.setCenterConstraintY(c);
        parent.addChildConstraint(c);
    }

}
