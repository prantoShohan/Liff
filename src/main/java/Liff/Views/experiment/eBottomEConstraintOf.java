package Liff.Views.experiment;

import qiwi.Variable;

public class eBottomEConstraintOf extends eConstraint {
    public eBottomEConstraintOf(eView v) {
        super(v);
    }

    @Override
    public Variable getTargetVariable() {
        return target.constraintWidget.vb;
    }
}
