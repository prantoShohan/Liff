package Liff.Views.experiment;

import qiwi.Variable;

public class eTopConstraintOf extends eConstraint {
    public eTopConstraintOf(eView v) {
        super(v);
    }

    @Override
    public Variable getTargetVariable() {
        return target.constraintWidget.vt;
    }
}
