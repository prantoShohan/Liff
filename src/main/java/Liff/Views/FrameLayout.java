package Liff.Views;

public class FrameLayout extends Layout{
    private View child;
    public FrameLayout(){

    }
    public void addChild(View v, LayoutParameters l){
        v.setParent(this);
        v.setLayoutParams(l);
        this.child = v;
    }



}
