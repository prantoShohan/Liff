package Liff.Components;

import Liff.Components.Component;

public class TestComponent extends Component {

    @Override
    public void init() {
        System.out.println("initializing test component");
    }

    @Override
    public void update(float dt) {
        System.out.println("updating test component");
    }
}
