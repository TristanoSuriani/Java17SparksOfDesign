package nl.suriani.java17.sparks.of.design.lightbulb;

public sealed interface Connected extends Acquired permits Off, On, Flickering {
    default NotConnected disconnect() {
        return new NotConnected();
    }

    default NonFunctioning reportNonFunctioning() {
        return new NonFunctioning();
    }
}
