import Liff.Renderable;

public class MyRender implements Renderable {
    @Override
    public void render(float dt) {
        System.out.println(1/dt);
    }
}
