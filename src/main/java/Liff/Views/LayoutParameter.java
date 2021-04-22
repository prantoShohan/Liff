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

}
