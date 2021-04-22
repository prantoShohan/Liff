import Liff.Application;
import qiwi.*;

public class Main {


    public static void main(String[] args) throws UnsatisfiableConstraintException, DuplicateConstraintException {


        Application app = new Application(740, 620, "Liff of a pimaina");
        app.setRenderObject(new MyRender());
        app.run();

//        Solver solver = new Solver();
//        Variable x = new Variable("x");
//        Variable y = new Variable("y");
//
//        // x = 20
//        solver.addConstraint(Symbolics.equals(x, 20));
//
//        // x + 2 == y + 10
//        solver.addConstraint(Symbolics.equals(Symbolics.add(x,2), Symbolics.add(y, 10)));
//
//        solver.updateVariables();
//
//        System.out.println("x " + x.getValue() + " y " + y.getValue());
        // x == 20
        // y == 12
    }
}
