package nl.suriani.java21.sparks.of.design.domain;

import nl.suriani.java21.sparks.of.design.domain.events.Event;

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

    default <T extends InventoryItem> boolean is(Class<T> clazz) {
        return clazz.isAssignableFrom(getClass());
    }

    default <T extends InventoryItem> T as(Class<T> clazz) {
        return clazz.cast(this);
    }
}
