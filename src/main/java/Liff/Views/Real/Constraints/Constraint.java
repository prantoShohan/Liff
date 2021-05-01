package Liff.Views.Real.Constraints;

import Liff.Views.Real.ConstraintLayout;
import Liff.Views.Real.ConstraintWidget;
import Liff.Views.Real.View;
import qiwi.Variable;

public abstract class Constraint {
    protected View targetView;

    public Constraint(View targetView) {
        this.targetView = targetView;
    }

    public abstract Variable getTargetVariable(ConstraintLayout layout);
}
