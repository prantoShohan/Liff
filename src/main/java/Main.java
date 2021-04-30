import Liff.Application;
import Liff.Views.experiment.*;
import qiwi.*;

public class Main {




    public static void main(String[] args) throws UnsatisfiableConstraintException, DuplicateConstraintException {
        ConstraintLayout layout = new ConstraintLayout("Constraint_layout");
        View v1 = new View("v1");
        View v2 = new View("v2");
        View v3  = new View("v3");
        layout.addChild(v1);
        layout.addChild(v2);
        layout.addChild(v3);
        v1.constraintWidget.leftConstraint = new LeftConstraintOf(layout);
        v1.constraintWidget.rightConstraint = new LeftConstraintOf(v2);
        //v1.constraintWidget.horizontalSizeConstraint = new FreeSizeConstraint();
        v2.constraintWidget.leftConstraint = new RightConstraintOf(v1);
        v2.constraintWidget.rightConstraint = new RightConstraintOf(layout);
        v3.constraintWidget.leftConstraint = new RightConstraintOf(v1);
        v3.constraintWidget.rightConstraint = new LeftConstraintOf(v2);
        layout.addEquations();
        layout.updateVariables();
        System.out.println(v1.constraintWidget);
        System.out.println(v2.constraintWidget);
        System.out.println(v3.constraintWidget);
        System.out.println(layout.constraintWidget);


//        Application app = new Application(740, 620, "Liff of a pimaina");
//        app.setRenderObject(new MyRender());
//        app.run();

    }
}
