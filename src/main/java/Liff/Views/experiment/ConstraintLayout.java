package Liff.Views.experiment;


import qiwi.DuplicateConstraintException;
import qiwi.Solver;
import qiwi.Symbolics;
import qiwi.UnsatisfiableConstraintException;

import java.util.ArrayList;
import java.util.List;

public class ConstraintLayout extends View {
    public List<View> children = new ArrayList<>();
    public List<ConstraintWidget> constraintWidgets = new ArrayList<>();
    public Solver solver;

    public ConstraintLayout(String id) {
        super(id);
        solver = new Solver();
        //-------------
        this.constraintWidget.l = 0;
        this.constraintWidget.r = 800;
    }

    public void addEquations() throws UnsatisfiableConstraintException, DuplicateConstraintException {
        solver.addConstraint(Symbolics.equals(this.constraintWidget.vl, this.constraintWidget.l));
        solver.addConstraint(Symbolics.equals(this.constraintWidget.vr, this.constraintWidget.r));
        for (ConstraintWidget cw : constraintWidgets) {
            cw.createStates();
            cw.addEquation(solver);
        }
    }

    public void updateVariables() {
        solver.updateVariables();
        for (ConstraintWidget cw : constraintWidgets) {
            cw.updateVariables();
        }
    }

    public void addChild(View v) {
        v.setParent(this);
        constraintWidgets.add(v.constraintWidget);
        children.add(v);
    }

    @Override
    public String toString() {
        return "ConstraintLayout{" +
                "constraintWidgets=" + constraintWidgets +
                '}';
    }
}
