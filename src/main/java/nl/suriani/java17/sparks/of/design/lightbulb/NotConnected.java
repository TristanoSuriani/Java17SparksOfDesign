package nl.suriani.java17.sparks.of.design.lightbulb;

public record NotConnected() implements Acquired {
    public NonFunctioning reportBrokenState() {
        return new NonFunctioning();
    }

    public On plugInWithStream() {
        return new On();
    }

    public Off plugInWithoutStream() {
        return new Off();
    }
}
