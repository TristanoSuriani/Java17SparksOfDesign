package nl.suriani.java17.sparks.of.design.match.domain;

public abstract class Value<T> {
    private final T value;

    public Value(T value) {
        Guard.isNotNull(value);
        this.value = value;
    }

    public T value() {
        return value;
    }
}
