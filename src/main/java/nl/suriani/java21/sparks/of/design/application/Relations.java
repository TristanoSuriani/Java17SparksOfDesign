package nl.suriani.java21.sparks.of.design.application;

import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public interface Relations {
    static Set<Relation> findByName(Set<Relation> relations, String name) {
        return relations.stream()
                .filter(relation -> relation.name().equals(name))
                .collect(Collectors.toSet());
    }

    static <T> Set<Relation<T, ?>> whereSubject(Set<Relation<T, ?>> relations, Predicate<T> predicate) {
        return relations.stream()
                .filter(relation -> predicate.test(relation.subject()))
                .collect(Collectors.toSet());
    }

    static <T> Set<Relation<?, T>> whereObject(Set<Relation<?, T>> relations, Predicate<T> predicate) {
        return relations.stream()
                .filter(relation -> predicate.test(relation.object()))
                .collect(Collectors.toSet());
    }
}
