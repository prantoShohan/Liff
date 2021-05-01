package Liff.Views.Real.Constraints;

public class FixedSizeConstraint extends SizeConstraint{
    private int size;
    public FixedSizeConstraint(int size) {
        this.size = size;
    }

    @Override
    public int getValue() {
        return this.size;
    }
}
