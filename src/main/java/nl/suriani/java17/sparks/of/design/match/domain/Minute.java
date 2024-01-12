package nl.suriani.java17.sparks.of.design.match.domain;


public class Minute extends Value<Integer> {
    public Minute(Integer value) {
        super(value);
        Guard.value(value)
                .isBetween(0)
                .and(135);
    }
}
