package nl.suriani.java17.sparks.of.design.lightbulb;

public record Flickering() implements Connected {
    public On stopFlickering() {
        return new On();
    }

    public Off turnOff() {
        return new Off();
    }
}
