package Liff.Views.experiment;

import qiwi.Variable;

public class eRightConstraintOf extends eConstraint {
    public eRightConstraintOf(eView v) {
        super(v);
    }

    @Override
    public Variable getTargetVariable() {
        return target.constraintWidget.vr;
    }
}
