package nl.suriani.java17.sparks.of.design.lightbulb;

public record Off() implements Connected {
    public On turnOn() {
        return new On();
    }
}
