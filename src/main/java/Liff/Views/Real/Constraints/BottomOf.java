package Liff.Views.Real.Constraints;

import Liff.Views.Real.ConstraintLayout;
import Liff.Views.Real.View;
import qiwi.Variable;

public class BottomOf extends Constraint{
    public BottomOf(View targetView) {
        super(targetView);
    }

    @Override
    public Variable getTargetVariable(ConstraintLayout layout) {
        return layout.getCorrespondingWidget(targetView).getVariable_bottom();
    }
}
