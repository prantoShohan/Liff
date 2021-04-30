package Liff.Views;

public class ViewSize {
    enum Behaviour{
        FIXED,
        WRAP_CONTENTS,
        MATCH_PARENT
    }
    private float x, y;
    private Behaviour xBehaviour;
    private Behaviour yBehaviour;
}
