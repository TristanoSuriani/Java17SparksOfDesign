package nl.suriani.java17.sparks.of.design.domain;

import nl.suriani.java17.sparks.of.design.domain.events.Event;

import java.util.List;

public sealed interface InventoryItem permits InStock, NoItem, OutOfStock, Removed {
    InventoryItem evolve(Event event);

    static InventoryItem project(List<Event> events) {
        InventoryItem state = new NoItem();
        for (Event event : events) {
            state = state.evolve(event);
        }
        return state;
    }
}
