package nl.suriani.java21.sparks.of.design.application;

public record Relation<X, Y>(String name, X subject, Y object) {
}
