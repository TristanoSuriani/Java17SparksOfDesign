package nl.suriani.java17.sparks.of.design.domain;

import nl.suriani.java17.sparks.of.design.domain.entities.ItemId;
import nl.suriani.java17.sparks.of.design.domain.entities.ItemInfo;
import nl.suriani.java17.sparks.of.design.domain.events.Event;
import nl.suriani.java17.sparks.of.design.domain.validation.Guards;

public record Removed(ItemId id, ItemInfo info) implements InventoryItem {
    public Removed {
        Guards.isNotNull(id);
        Guards.isNotNull(info);
    }

    @Override
    public InventoryItem evolve(Event event) {
        throw new IllegalStateException("Unexpected event type: " + event.type());
    }
}
