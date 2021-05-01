package Liff.Views.ConstraintLayout.Constraints.PositionConstraints;


import Liff.Views.ConstraintLayout.ConstraintLayout;
import Liff.Views.UiView;
import Liff.Views.View;
import qiwi.Variable;

public class RightOf extends Constraint {
    public RightOf(UiView targetView) {
        super(targetView);
    }

    @Override
    public Variable getTargetVariable(ConstraintLayout layout) {
        return layout.getCorrespondingWidget(targetView).getVariable_right();
    }
}
