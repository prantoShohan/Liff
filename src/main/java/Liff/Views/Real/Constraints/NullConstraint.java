package Liff.Views.Real.Constraints;

import Liff.Views.Real.ConstraintLayout;
import Liff.Views.Real.ConstraintWidget;
import Liff.Views.Real.View;
import qiwi.Variable;

public class NullConstraint extends Constraint{
    public NullConstraint(View targetView) {
        super(targetView);
    }

    @Override
    public Variable getTargetVariable(ConstraintLayout layout) {
        return null;
    }
}
