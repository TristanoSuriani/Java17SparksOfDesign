package nl.suriani.java21.sparks.of.design.domain;

import nl.suriani.java21.sparks.of.design.domain.entities.ItemId;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InventoryItemRepositoryStub implements InventoryItemRepository {
    private final Map<ItemId, InventoryItem> items = new HashMap<>();

    @Override
    public void save(InventoryItem item) {
        var id = switch (item) {
            case InStock inStock -> inStock.id();
            case NoItem noItem -> throw new IllegalStateException("cannot save an item that doesn't exist");
            case OutOfStock outOfStock -> outOfStock.id();
            case Removed removed -> removed.id();
        };

        items.put(id, item);
    }

    @Override
    public Optional<InventoryItem> findById(ItemId id) {
        return Optional.ofNullable(items.get(id));
    }
}
