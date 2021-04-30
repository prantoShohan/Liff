package Liff.Views.experiment;

import qiwi.Variable;

public class RightConstraintOf extends Constraint {
    public RightConstraintOf(View v) {
        super(v);
    }

    @Override
    public Variable getTargetVariable() {
        return target.constraintWidget.vr;
    }
}
