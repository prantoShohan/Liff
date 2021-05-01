package Liff.Views.experiment;


import qiwi.Variable;

public abstract class eConstraint {
    public eView target;
    public abstract Variable getTargetVariable();
    public eConstraint(eView v){
        target = v;
    }

}