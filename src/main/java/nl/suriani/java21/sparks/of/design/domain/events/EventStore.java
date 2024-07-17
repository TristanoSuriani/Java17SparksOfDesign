package nl.suriani.java21.sparks.of.design.domain.events;

import java.util.List;

public interface EventStore<E> {
    List<E> findById(String aggregateId);
    void publish(E event);
}
