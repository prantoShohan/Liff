package Liff.Views.experiment;

import qiwi.Variable;

public class BottomConstraintOf extends Constraint {
    public BottomConstraintOf(View v) {
        super(v);
    }

    @Override
    public Variable getTargetVariable() {
        return target.constraintWidget.vb;
    }
}
