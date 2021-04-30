package Liff.Views.experiment;

public class View{
    public String id;
    public ConstraintWidget constraintWidget;
    public ConstraintLayout parent;


    public View(String id){
        this.id = id;
        constraintWidget = new ConstraintWidget(this);
    }

    public void setParent(ConstraintLayout l){
        parent = l;
    }
}
