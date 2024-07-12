package nl.suriani.java17.sparks.of.design.lightbulb;

public record NoLightbulb() implements Lightbulb {
    public NonFunctioning acquireInBrokenState() {
        return new NonFunctioning();
    }

    public NotConnected acquire() {
        return new NotConnected();
    }
}
