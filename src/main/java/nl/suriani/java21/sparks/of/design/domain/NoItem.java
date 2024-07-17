package nl.suriani.java21.sparks.of.design.domain;

import nl.suriani.java21.sparks.of.design.domain.events.Event;

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
