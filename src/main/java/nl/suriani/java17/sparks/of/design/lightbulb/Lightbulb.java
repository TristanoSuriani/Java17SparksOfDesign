package nl.suriani.java17.sparks.of.design.lightbulb;

public sealed interface Lightbulb permits Acquired, Disposed, NoLightbulb {
}
