import Liff.Views.Real.ConstraintLayout;
import Liff.Views.Real.Constraints.BottomOf;
import Liff.Views.Real.Constraints.LeftOf;
import Liff.Views.Real.Constraints.RightOf;
import Liff.Views.Real.Constraints.TopOf;
import Liff.Views.Real.View;
import Liff.Views.experiment.*;
import qiwi.*;

public class Main {
    public static void main(String[] args) throws UnsatisfiableConstraintException, DuplicateConstraintException {
//        eConstraintLayout layout = new eConstraintLayout("Constraint_layout");
//        eView v1 = new eView("v1");
//        eView v2 = new eView("v2");
//        eView v3  = new eView("v3");
//        layout.addChild(v1);
//        layout.addChild(v2);
//        layout.addChild(v3);
//        v1.constraintWidget.leftConstraint = new eLeftConstraintOf(layout);
//        v2.constraintWidget.leftConstraint = new eRightConstraintOf(v1);
//        v2.constraintWidget.rightConstraint = new eLeftConstraintOf(v3);
//        v2.constraintWidget.horizontalSizeConstraint = new eFreeSizeConstraint();
//        //v3.constraintWidget.leftConstraint = new eRightConstraintOf(v2);
//        v3.constraintWidget.rightConstraint = new eRightConstraintOf(layout);
//
//
//        layout.addEquations();
//        layout.updateVariables();
//        System.out.println(v1.constraintWidget);
//        System.out.println(v2.constraintWidget);
//        System.out.println(v3.constraintWidget);
//        System.out.println(layout.constraintWidget);

        ConstraintLayout layout = new ConstraintLayout("root", 800, 800);
        View v1 = new View("v1");
        View v2 = new View("v2");
        View v3 = new View("v3");
        layout.addChild(v1);
        layout.addChild(v2);
        layout.addChild(v3);

        layout.setRightConstraint(v1, new LeftOf(v2));
        layout.setLeftConstraint(v1, new LeftOf(layout));
        layout.setBottomConstraint(v1, new BottomOf(layout));
        layout.setTopConstraint(v1, new TopOf(layout));

        layout.setRightConstraint(v2, new RightOf(layout));
        layout.setLeftConstraint(v2, new RightOf(v1));
        layout.setBottomConstraint(v2, new BottomOf(layout));
        layout.setTopConstraint(v2, new TopOf(v1));

        layout.updateConstraints();
        layout.updateChildrenVariables();

        System.out.println(layout);



//        Application app = new Application(740, 620, "Liff of a pimaina");
//        app.setRenderObject(new MyRender());
//        app.run();

    }
}

