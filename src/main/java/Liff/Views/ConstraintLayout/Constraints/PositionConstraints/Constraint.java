package Liff.Views.ConstraintLayout.Constraints.PositionConstraints;

import Liff.Views.ConstraintLayout.ConstraintLayout;
import Liff.Views.UiView;
import Liff.Views.View;
import qiwi.Variable;

public abstract class Constraint {
    protected UiView targetView;

    public Constraint(UiView targetView) {
        this.targetView = targetView;
    }

    public abstract Variable getTargetVariable(ConstraintLayout layout);
}
