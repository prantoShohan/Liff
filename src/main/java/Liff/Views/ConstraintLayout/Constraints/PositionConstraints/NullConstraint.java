package Liff.Views.ConstraintLayout.Constraints.PositionConstraints;

import Liff.Views.ConstraintLayout.ConstraintLayout;
import Liff.Views.View;
import qiwi.Variable;

public class NullConstraint extends Constraint {
    public NullConstraint(View targetView) {
        super(targetView);
    }

    @Override
    public Variable getTargetVariable(ConstraintLayout layout) {
        return null;
    }
}
