package nl.suriani.java17.sparks.of.design.inventory;

import nl.suriani.java17.sparks.of.design.inventory.entities.ItemId;
import nl.suriani.java17.sparks.of.design.inventory.entities.ItemInfo;
import nl.suriani.java17.sparks.of.design.inventory.entities.StockAmount;
import nl.suriani.java17.sparks.of.design.inventory.events.Event;
import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

public record InStock(ItemId id, ItemInfo info, StockAmount amount) implements InventoryItem {
    public InStock {
        Guards.isNotNull(id);
        Guards.isNotNull(info);
        Guards.isNotNull(amount);
    }

    public Removed remove() {
        return new Removed(id, info);
    }

    public OutOfStock outOfStock() {
        return new OutOfStock(id, info);
    }

    public InStock increaseStockAmount(StockAmount amount) {
        return new InStock(id, info, this.amount.add(amount));
    }

    public InStock decreaseStockAmount(StockAmount amount) {
        return new InStock(id, info, this.amount.subtract(amount));
    }

    @Override
    public InventoryItem evolve(Event event) {
        return switch (event) {
            case Event.ItemRemoved itemRemoved -> remove();
            case Event.StockDecreased stockDecreased -> decreaseStockAmount(stockDecreased.amount());
            case Event.StockIncreased stockIncreased -> increaseStockAmount(stockIncreased.amount());
            case Event.PutOutOfStock putOutOfStock -> outOfStock();
            default -> throw new IllegalStateException("Unexpected event type: " + event.type());
        };
    }
}
