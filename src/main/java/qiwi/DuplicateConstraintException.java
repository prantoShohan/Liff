package qiwi;

/**
 * Created by alex on 30/01/15.
 */
public class DuplicateConstraintException extends KiwiException {

    private qConstraint qConstraint;

    public DuplicateConstraintException(qConstraint qConstraint) {
        this.qConstraint = qConstraint;
    }
}
