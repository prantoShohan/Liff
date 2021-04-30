package Liff.Views;

public class View {
    private View parent;
    private String id;
    private LayoutParameters lParams;

    public View(){

    }

    public void setParent(Layout l){
        this.parent = l;
    }

    public void setLayoutParams(LayoutParameters l){
        this.lParams = l;
    }


}
