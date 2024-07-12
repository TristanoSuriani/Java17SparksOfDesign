package nl.suriani.java17.sparks.of.design.lightbulb;

public sealed interface Acquired extends Lightbulb permits Connected, NotConnected, NonFunctioning {
    default Disposed dispose() {
        return new Disposed();
    }
}
