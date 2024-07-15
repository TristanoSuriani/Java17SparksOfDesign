package nl.suriani.java17.sparks.of.design.inventory;

import nl.suriani.java17.sparks.of.design.inventory.events.Event;

public record NoItem() implements InventoryItem {
    @Override
    public InventoryItem evolve(Event event) {
        return switch (event) {
            case Event.ItemAddedWithoutStock itemAddedWithoutStock ->
                    new OutOfStock(itemAddedWithoutStock.id(),
                            itemAddedWithoutStock.info()
                    );

            case Event.ItemAddedWithStock itemAddedWithStock ->
                    new InStock(itemAddedWithStock.id(),
                            itemAddedWithStock.info(),
                            itemAddedWithStock.amount()
                    );

            default -> throw new IllegalStateException("Unexpected event type: " + event.type());
        };
    }
}
