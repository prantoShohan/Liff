package Liff.Views.experiment;

import qiwi.Variable;

public class eLeftConstraintOf extends eConstraint {
    public eLeftConstraintOf(eView v) {
        super(v);
    }

    @Override
    public Variable getTargetVariable() {
        return target.constraintWidget.vl;
    }
}
