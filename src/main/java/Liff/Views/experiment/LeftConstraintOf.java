package Liff.Views.experiment;

import qiwi.Variable;

public class LeftConstraintOf extends Constraint {
    public LeftConstraintOf(View v) {
        super(v);
    }

    @Override
    public Variable getTargetVariable() {
        return target.constraintWidget.vl;
    }
}
