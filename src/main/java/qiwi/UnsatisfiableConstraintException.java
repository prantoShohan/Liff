package qiwi;

/**
 * Created by alex on 30/01/15.
 */
public class UnsatisfiableConstraintException extends KiwiException {

    private qConstraint qConstraint;
    public UnsatisfiableConstraintException(qConstraint qConstraint) {
        super(qConstraint.toString());
        this.qConstraint = qConstraint;
    }
}
