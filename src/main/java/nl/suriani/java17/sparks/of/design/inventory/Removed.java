package nl.suriani.java17.sparks.of.design.inventory;

import nl.suriani.java17.sparks.of.design.inventory.entities.ItemId;
import nl.suriani.java17.sparks.of.design.inventory.entities.ItemInfo;
import nl.suriani.java17.sparks.of.design.inventory.events.Event;
import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

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
