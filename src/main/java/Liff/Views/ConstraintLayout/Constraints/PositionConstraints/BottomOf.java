package Liff.Views.ConstraintLayout.Constraints.PositionConstraints;

import Liff.Views.ConstraintLayout.ConstraintLayout;

import Liff.Views.UiView;
import qiwi.Variable;

public class BottomOf extends Constraint {
    public BottomOf(UiView targetView) {
        super(targetView);
    }

    @Override
    public Variable getTargetVariable(ConstraintLayout layout) {
        return layout.getCorrespondingWidget(targetView).getVariable_bottom();
    }
}
