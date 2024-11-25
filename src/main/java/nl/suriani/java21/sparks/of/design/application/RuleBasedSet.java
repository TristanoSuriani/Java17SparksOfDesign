package nl.suriani.java21.sparks.of.design.application;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public record RuleBasedSet<T> (Predicate<T> belongsTo) {
    public boolean contains(T element) {
        return belongsTo.test(element);
    }

    public Set<T> intersection(Set<T> other) {
        return other.stream()
                .filter(this::contains)
                .collect(Collectors.toSet());
    }

    public RuleBasedSet<T> intersection(RuleBasedSet<T> other) {
        return new RuleBasedSet<T>(belongsTo.and(other.belongsTo));
    }

    public boolean intersects(Set<T> other) {
        return intersection(other).isEmpty();
    }

    public RuleBasedSet<T> union(RuleBasedSet<T> other) {
        return new RuleBasedSet<T>(belongsTo.or(other.belongsTo));
    }
}
