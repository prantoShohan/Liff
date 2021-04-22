import Liff.Application;
import qiwi.*;

public class Main {


    public static void main(String[] args) throws UnsatisfiableConstraintException, DuplicateConstraintException {


        Application app = new Application(740, 620, "Liff of a pimaina");
        app.setRenderObject(new MyRender());
        app.run();

    }
}
