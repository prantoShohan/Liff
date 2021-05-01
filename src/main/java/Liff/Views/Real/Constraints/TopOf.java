package Liff.Views.Real.Constraints;

import Liff.Views.Real.ConstraintLayout;
import Liff.Views.Real.View;
import qiwi.Variable;

public class TopOf extends Constraint{
    public TopOf(View targetView) {
        super(targetView);
    }

    @Override
    public Variable getTargetVariable(ConstraintLayout layout) {
        return layout.getCorrespondingWidget(targetView).getVariable_top();
    }
}
