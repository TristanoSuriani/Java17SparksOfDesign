package nl.suriani.java17.sparks.of.design.domain.events;

import java.util.List;

public interface EventStore<E> {
    List<E> findById(String aggregateId);
    void save(E event);
}
