package nl.suriani.java21.sparks.of.design.domain;

import nl.suriani.java21.sparks.of.design.domain.events.Event;
import nl.suriani.java21.sparks.of.design.domain.events.EventStore;

import java.util.*;

public class EventStoreStub implements EventStore<Event> {
    private final Map<String, List<Event>> events = new HashMap<>();

    @Override
    public List<Event> findById(String aggregateId) {
        return Optional.ofNullable(events.get(aggregateId))
                .orElse(List.of());
    }

    @Override
    public void publish(Event event) {
        var relevantEvents = Optional.ofNullable(events.get(event.id().value().toString()))
                .map(ArrayList::new)
                .orElse(new ArrayList<>());

        relevantEvents.add(event);
        events.put(event.id().value().toString(), List.copyOf(relevantEvents));
    }
}
