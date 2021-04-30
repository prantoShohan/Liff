package Liff.Views.experiment;

import qiwi.*;

public class ConstraintWidget {
    public View source;
    public Constraint leftConstraint = null;
    public Constraint rightConstraint = null;
    public Constraint topConstraint = null;
    public Constraint bottomConstraint = null;
    public SizeConstraint horizontalSizeConstraint = null;
    public SizeConstraint verticalSizeConstraint = null;

    public Variable vr;
    public Variable vl;
    public Variable vt;
    public Variable vb;
    public Variable vw;
    public Variable vh;

    public float r = 0, l = 0, t = 0, b = 0, w = 0, h = 0;
    public float mr = 16, ml = 16, mt = 16, mb = 16;

    public boolean centerHorizontal;
    public boolean leftAlign;
    public boolean rightAlign;

    public ConstraintWidget(View source) {
        this.source = source;
        vr = new Variable(source.id + "_r");
        vl = new Variable(source.id + "_l");
        vt = new Variable(source.id + "_t");
        vb = new Variable(source.id + "_b");
        vw = new Variable(source.id + "_w");
        vh = new Variable(source.id + "_h");
    }

    public void createStates() {
        if (leftConstraint != null && rightConstraint != null) {
            centerHorizontal = true;
            leftAlign = false;
            rightAlign = false;
        } else if (leftConstraint != null) {
            leftAlign = true;
            rightAlign = false;
            centerHorizontal = false;
        } else {
            rightAlign = true;
            leftAlign = false;
            centerHorizontal = false;
        }
        if(horizontalSizeConstraint == null){
            horizontalSizeConstraint = new FixedSizeConstraint(100);
        }
        if(verticalSizeConstraint == null){
            verticalSizeConstraint = new FixedSizeConstraint(50);
        }
    }

    public void addEquation(Solver s) throws UnsatisfiableConstraintException, DuplicateConstraintException {
        s.addConstraint(Symbolics.greaterThanOrEqualTo(Symbolics.subtract(this.vl, ml), source.parent.constraintWidget.vl));
        s.addConstraint(Symbolics.lessThanOrEqualTo(Symbolics.add(this.vr, mr), source.parent.constraintWidget.vr));
        if(horizontalSizeConstraint instanceof FreeSizeConstraint ){
            if(centerHorizontal){
                s.addConstraint(Symbolics.equals(Symbolics.subtract(this.vl, ml), leftConstraint.getTargetVariable()));
                s.addConstraint(Symbolics.equals(Symbolics.add(this.vr, mr), rightConstraint.getTargetVariable()));
            }else{
                horizontalSizeConstraint = new FixedSizeConstraint(100);
            }
        }
        if(horizontalSizeConstraint instanceof FixedSizeConstraint) {
            s.addConstraint(Symbolics.equals(this.vw, horizontalSizeConstraint.size));
            if (centerHorizontal) {
                s.addConstraint(Symbolics.equals(this.vl, Symbolics.divide(Symbolics.subtract(Symbolics.add(leftConstraint.getTargetVariable(), rightConstraint.getTargetVariable()), this.vw), 2)));
                s.addConstraint(Symbolics.equals(this.vr, Symbolics.add(this.vl, this.vw)));
            } else if (leftAlign) {
                s.addConstraint(Symbolics.equals(Symbolics.subtract(this.vl, ml), leftConstraint.getTargetVariable()));
                s.addConstraint(Symbolics.equals(this.vr, Symbolics.add(this.vl, this.vw)));
            } else if (rightAlign) {
                s.addConstraint(Symbolics.equals(Symbolics.add(this.vr, mr), rightConstraint.getTargetVariable()));
                s.addConstraint(Symbolics.equals(this.vl, Symbolics.subtract(this.vr, this.vw)));
            }
        }

    }

    public void updateVariables() {
        this.l = (float) this.vl.getValue();
        this.r = (float) this.vr.getValue();
        this.w = (float) this.vw.getValue();
    }

    @Override
    public String toString() {
        return "ConstraintWidget{" +
                "r=" + r +
                ", l=" + l +
                ", t=" + t +
                ", b=" + b +
                ", w=" + w +
                ", h=" + h +
                "}\n";
    }
}
