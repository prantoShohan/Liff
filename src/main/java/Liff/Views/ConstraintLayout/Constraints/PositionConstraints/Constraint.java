package Liff.Views.ConstraintLayout.Constraints.PositionConstraints;

import Liff.Views.ConstraintLayout.ConstraintLayout;
import Liff.Views.View;
import qiwi.Variable;

public abstract class Constraint {
    protected View targetView;

    public Constraint(View targetView) {
        this.targetView = targetView;
    }

    public abstract Variable getTargetVariable(ConstraintLayout layout);
}
