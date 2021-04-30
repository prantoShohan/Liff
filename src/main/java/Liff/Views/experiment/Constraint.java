package Liff.Views.experiment;


import qiwi.Variable;

public abstract class Constraint{
    public View target;
    public abstract Variable getTargetVariable();
    public Constraint(View v){
        target = v;
    }

}