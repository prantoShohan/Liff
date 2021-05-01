package Liff.Views.experiment;

public class eView {
    public String id;
    public eConstraintWidget constraintWidget;
    public eConstraintLayout parent;



    public eView(String id){
        this.id = id;
        constraintWidget = new eConstraintWidget(this);
    }

    public void setParent(eConstraintLayout l){
        parent = l;
    }
}
