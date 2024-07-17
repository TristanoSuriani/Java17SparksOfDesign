package nl.suriani.java21.sparks.of.design.domain;

import nl.suriani.java21.sparks.of.design.domain.entities.ItemId;
import nl.suriani.java21.sparks.of.design.domain.entities.ItemInfo;
import nl.suriani.java21.sparks.of.design.domain.entities.StockAmount;
import nl.suriani.java21.sparks.of.design.domain.events.Event;
import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record OutOfStock(ItemId id, ItemInfo info) implements InventoryItem {
    public OutOfStock {
        Guards.isNotNull(id);
        Guards.isNotNull(info);
    }

    public Removed remove() {
        return new Removed(id, info);
    }

    public InStock increaseStockAmount(StockAmount amount) {
        return new InStock(id, info, amount);
    }

    @Override
    public InventoryItem evolve(Event event) {
        return switch (event) {
            case Event.ItemRemoved itemRemoved -> remove();
            case Event.StockIncreased stockIncreased -> increaseStockAmount(stockIncreased.amount());
            default -> throw new IllegalStateException("Unexpected event type: " + event.type());
        };
    }
}
