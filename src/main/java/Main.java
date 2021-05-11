
import Liff.Core.Application;


public class Main {

    public static void main(String[] args) {

        Application app = new Application(740, 620, "Liff of a pimaina");
        app.setRenderObject(new MyRender());
        app.run();
   }
}

