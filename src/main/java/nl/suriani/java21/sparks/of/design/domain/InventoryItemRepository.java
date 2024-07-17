package nl.suriani.java21.sparks.of.design.domain;

import nl.suriani.java21.sparks.of.design.domain.entities.ItemId;

import java.util.Optional;

public interface InventoryItemRepository {
    void save(InventoryItem item);

    Optional<InventoryItem> findById(ItemId id);
}
