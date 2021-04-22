package Liff.Views;

public class LayoutParameter {
    enum SpecBehaviour{
        WRAP_CONTENT,
        MATCH_CONSTRAINT,
        FIXED
    }

    private float width;
    private float height;
    private float wMargin;
    private float hMargin;

    private LayoutParameter wBehaviour;
    private LayoutParameter hBehaviour;

    private Constraint leftConstraint;
    private Constraint rightConstraint;
    private Constraint topConstraint;
    private Constraint bottomConstraint;

    private Constraint centerConstraintX;
    private Constraint centerConstraintY;

    public void setLeftConstraint(Constraint leftConstraint) {
        this.leftConstraint = leftConstraint;
    }

    public void setRightConstraint(Constraint rightConstraint) {
        this.rightConstraint = rightConstraint;
    }

    public void setTopConstraint(Constraint topConstraint) {
        this.topConstraint = topConstraint;
    }

    public void setBottomConstraint(Constraint bottomConstraint) {
        this.bottomConstraint = bottomConstraint;
    }

    public void setCenterConstraintX(Constraint centerConstraintX) {
        this.centerConstraintX = centerConstraintX;
    }

    public void setCenterConstraintY(Constraint centerConstraintY) {
        this.centerConstraintY = centerConstraintY;
    }
}
