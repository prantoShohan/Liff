package Liff.Views.ConstraintLayout;

import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.NullConstraint;
import Liff.Views.ConstraintLayout.Constraints.SizeConstraints.SizeConstraint;
import Liff.Views.ConstraintLayout.Constraints.PositionConstraints.Constraint;
import Liff.Views.ConstraintLayout.Constraints.SizeConstraints.FixedSizeConstraint;
import Liff.Views.ConstraintLayout.Constraints.SizeConstraints.FreeSizeConstraint;
import Liff.Views.Rectangle;
import qiwi.*;

public class ConstraintWidget {
    private static int constraintWidgetCount = 0;

    private Rectangle targetRectangle;
    private ConstraintLayout parentLayout;

    private Variable variable_left;
    private Variable variable_right;
    private Variable variable_bottom;
    private Variable variable_top;
    private Variable variable_width;
    private Variable variable_height;

    private float horizontalBias = 0.5f, verticalBias = 0.5f;

    private Constraint leftConstraint, rightConstraint;
    private Constraint bottomConstraint, topConstraint;
    private SizeConstraint widthConstraint, heightConstraint;

    private enum HorizontalBehaviour{CENTER_HORIZONTAL, LEFT_ALIGN, RIGHT_ALIGN, NO_CONSTRAINT};
    private enum VerticalBehaviour {CENTER_VERTICAL, BOTTOM_ALIGN, TOP_ALIGN, NO_CONSTRAINT};

    private HorizontalBehaviour horizontalBehaviour;
    private VerticalBehaviour verticalBehaviour;

    public ConstraintWidget(Rectangle targetRectangle, ConstraintLayout layout) {
        this.targetRectangle = targetRectangle;
        this.parentLayout = layout;

        variable_left = new Variable(constraintWidgetCount+"_LEFT");
        variable_right = new Variable(constraintWidgetCount+"_RIGHT");
        variable_bottom = new Variable(constraintWidgetCount+"_BOTTOM");
        variable_top = new Variable(constraintWidgetCount+"_TOP");
        variable_width = new Variable(constraintWidgetCount+"_WIDTH");
        variable_height = new Variable(constraintWidgetCount+"_HEIGHT");

        constraintWidgetCount++;

        leftConstraint = new NullConstraint(null);
        rightConstraint = new NullConstraint(null);
        bottomConstraint = new NullConstraint(null);
        topConstraint = new NullConstraint(null);

    }

    public void setLeftConstraint(Constraint leftConstraint) {
        this.leftConstraint = leftConstraint;
    }

    public void setRightConstraint(Constraint rightConstraint) {
        this.rightConstraint = rightConstraint;
    }

    public void setBottomConstraint(Constraint bottomConstraint) {
        this.bottomConstraint = bottomConstraint;
    }

    public void setTopConstraint(Constraint topConstraint) {
        this.topConstraint = topConstraint;
    }

    public void setWidthConstraint(SizeConstraint widthConstraint) {
        this.widthConstraint = widthConstraint;
    }

    public void setHeightConstraint(SizeConstraint heightConstraint) {
        this.heightConstraint = heightConstraint;
    }

    public Variable getVariable_left() { return variable_left; }

    public Variable getVariable_right() { return variable_right; }

    public Variable getVariable_bottom() { return variable_bottom; }

    public Variable getVariable_top() { return variable_top; }

    public Variable getVariable_width() { return variable_width; }

    public Variable getVariable_height() { return variable_height; }


    public void updateBehaviours(){
        if(leftConstraint instanceof NullConstraint && rightConstraint instanceof NullConstraint){
            horizontalBehaviour = HorizontalBehaviour.NO_CONSTRAINT;
        }else if(leftConstraint instanceof NullConstraint){
            horizontalBehaviour = HorizontalBehaviour.RIGHT_ALIGN;
        }else if(rightConstraint instanceof NullConstraint){
            horizontalBehaviour = HorizontalBehaviour.LEFT_ALIGN;
        }else{
            horizontalBehaviour = HorizontalBehaviour.CENTER_HORIZONTAL;
        }

        if(topConstraint instanceof NullConstraint && bottomConstraint instanceof NullConstraint){
            verticalBehaviour = VerticalBehaviour.NO_CONSTRAINT;
        }else if(topConstraint instanceof NullConstraint){
            verticalBehaviour = VerticalBehaviour.BOTTOM_ALIGN;
        }else if(bottomConstraint instanceof NullConstraint){
            verticalBehaviour = VerticalBehaviour.TOP_ALIGN;
        }else{
            verticalBehaviour = VerticalBehaviour.CENTER_VERTICAL;
        }

        if(widthConstraint == null){
            widthConstraint = new FixedSizeConstraint(targetRectangle.width);
        }
        if(heightConstraint  == null){
            heightConstraint = new FixedSizeConstraint(targetRectangle.height);
        }
    }

    public void addConstraints(Solver solver) throws UnsatisfiableConstraintException, DuplicateConstraintException {

        solver.addConstraint(Symbolics.greaterThanOrEqualTo(Symbolics.subtract(this.variable_left, this.targetRectangle.margin_left), parentLayout.getConstraintWidget().getVariable_left()));
        solver.addConstraint(Symbolics.lessThanOrEqualTo(Symbolics.add(this.variable_right, this.targetRectangle.margin_right), parentLayout.getConstraintWidget().getVariable_right()));
        solver.addConstraint(Symbolics.greaterThanOrEqualTo(Symbolics.subtract(this.variable_bottom, this.targetRectangle.margin_bottom), parentLayout.getConstraintWidget().getVariable_bottom()));
        solver.addConstraint(Symbolics.lessThanOrEqualTo(Symbolics.add(this.variable_top, this.targetRectangle.margin_top), parentLayout.getConstraintWidget().getVariable_top()));

        solver.addConstraint(Symbolics.equals(Symbolics.add(this.variable_left, this.variable_width), this.variable_right));
        solver.addConstraint(Symbolics.equals(Symbolics.add(this.variable_bottom, this.variable_height), this.variable_top));

        if(widthConstraint instanceof FreeSizeConstraint){
            if(horizontalBehaviour == HorizontalBehaviour.CENTER_HORIZONTAL){
                solver.addConstraint(Symbolics.equals(Symbolics.subtract(this.variable_left, this.targetRectangle.margin_left),leftConstraint.getTargetVariable(parentLayout)));
                solver.addConstraint(Symbolics.equals(Symbolics.add(this.variable_right, this.targetRectangle.margin_right),rightConstraint.getTargetVariable(parentLayout)));
            }else{
                widthConstraint = new FixedSizeConstraint(targetRectangle.width);
            }
        }
        if(widthConstraint instanceof FixedSizeConstraint){
            solver.addConstraint(Symbolics.equals(this.variable_width, widthConstraint.getValue()));
            if(horizontalBehaviour == HorizontalBehaviour.CENTER_HORIZONTAL){
                solver.addConstraint(Symbolics.equals(this.variable_left, Symbolics.divide(Symbolics.subtract(Symbolics.add(leftConstraint.getTargetVariable(parentLayout), rightConstraint.getTargetVariable(parentLayout)), this.variable_width), 1/horizontalBias)));
                solver.addConstraint(Symbolics.equals(this.variable_right, Symbolics.add(this.variable_left, variable_width)));
            }else if(horizontalBehaviour == HorizontalBehaviour.LEFT_ALIGN){
                solver.addConstraint(Symbolics.equals(Symbolics.subtract(this.variable_left, this.targetRectangle.margin_left), leftConstraint.getTargetVariable(parentLayout)));
                solver.addConstraint(Symbolics.equals(this.variable_right, Symbolics.add(this.variable_left, variable_width)));
            }else if(horizontalBehaviour == HorizontalBehaviour.RIGHT_ALIGN){
                solver.addConstraint(Symbolics.equals(Symbolics.add(this.variable_right, this.targetRectangle.margin_right), rightConstraint.getTargetVariable(parentLayout)));
                solver.addConstraint(Symbolics.equals(this.variable_left, Symbolics.subtract(this.variable_right, this.variable_width)));
            }
        }

        if(heightConstraint instanceof FreeSizeConstraint){
            if(verticalBehaviour == VerticalBehaviour.CENTER_VERTICAL){
                solver.addConstraint(Symbolics.equals(Symbolics.subtract(this.variable_bottom, this.targetRectangle.margin_bottom),bottomConstraint.getTargetVariable(parentLayout)));
                solver.addConstraint(Symbolics.equals(Symbolics.add(this.variable_top, this.targetRectangle.margin_top),topConstraint.getTargetVariable(parentLayout)));
            }else{
                heightConstraint = new FixedSizeConstraint(targetRectangle.height);
            }
        }
        if(heightConstraint instanceof FixedSizeConstraint){
            solver.addConstraint(Symbolics.equals(this.variable_height, heightConstraint.getValue()));
            if(verticalBehaviour == VerticalBehaviour.CENTER_VERTICAL){
                solver.addConstraint(Symbolics.equals(this.variable_bottom, Symbolics.divide(Symbolics.subtract(Symbolics.add(bottomConstraint.getTargetVariable(parentLayout), topConstraint.getTargetVariable(parentLayout)), this.variable_height), 1/verticalBias)));
                solver.addConstraint(Symbolics.equals(this.variable_top, Symbolics.add(this.variable_bottom, variable_height)));
            }else if(verticalBehaviour == VerticalBehaviour.BOTTOM_ALIGN){
                solver.addConstraint(Symbolics.equals(Symbolics.subtract(this.variable_bottom, this.targetRectangle.margin_bottom), bottomConstraint.getTargetVariable(parentLayout)));
                solver.addConstraint(Symbolics.equals(this.variable_top, Symbolics.add(this.variable_bottom, variable_height)));
            }else if(verticalBehaviour == VerticalBehaviour.TOP_ALIGN){
                solver.addConstraint(Symbolics.equals(Symbolics.add(this.variable_top, this.targetRectangle.margin_top), topConstraint.getTargetVariable(parentLayout)));
                solver.addConstraint(Symbolics.equals(this.variable_bottom, Symbolics.subtract(this.variable_top, this.variable_height)));
            }
        }
    }

    public void updateTargetRectangle(){
        this.targetRectangle.posX = (int) this.variable_left.getValue();
        this.targetRectangle.posY = (int) this.variable_bottom.getValue();
        this.targetRectangle.sizeX = (int) this.variable_right.getValue();
        this.targetRectangle.sizeY = (int) this.variable_top.getValue();
        this.targetRectangle.width = (int) this.variable_width.getValue();
        this.targetRectangle.height = (int) this.variable_height.getValue();
    }



}
