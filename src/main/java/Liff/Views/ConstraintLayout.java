package Liff.Views;

import qiwi.qConstraint;

import java.util.Hashtable;
import java.util.List;

public class ConstraintLayout extends ViewGroup{
    private Hashtable<String, View> children;

    private List<Constraint> constraints;

    public void addChildConstraint(Constraint c){
        constraints.add(c);
    }



}
