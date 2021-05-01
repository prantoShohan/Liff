package Liff.Views.ConstraintLayout.Constraints.PositionConstraints;

import Liff.Views.ConstraintLayout.ConstraintLayout;
import Liff.Views.View;
import qiwi.Variable;

public class LeftOf extends Constraint {
    public LeftOf(View targetView) {
        super(targetView);
    }

    @Override
    public Variable getTargetVariable(ConstraintLayout layout) {
        return layout.getCorrespondingWidget(targetView).getVariable_left();
    }
}
