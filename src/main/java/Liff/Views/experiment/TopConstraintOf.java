package Liff.Views.experiment;

import qiwi.Variable;

public class TopConstraintOf extends Constraint {
    public TopConstraintOf(View v) {
        super(v);
    }

    @Override
    public Variable getTargetVariable() {
        return target.constraintWidget.vt;
    }
}
