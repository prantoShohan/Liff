package Liff.Views.Real.Constraints;


import Liff.Views.Real.ConstraintLayout;
import Liff.Views.Real.ConstraintWidget;
import Liff.Views.Real.View;
import qiwi.Variable;

public class RightOf extends Constraint {
    public RightOf(View targetView) {
        super(targetView);
    }

    @Override
    public Variable getTargetVariable(ConstraintLayout layout) {
        return layout.getCorrespondingWidget(targetView).getVariable_right();
    }
}
