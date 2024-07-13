package nl.suriani.java17.sparks.of.design.inventory.entities;

import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

import java.util.UUID;

public record ItemId(UUID value) {
    public ItemId {
        Guards.isNotNull(value);
    }

    public ItemId() {
        this(UUID.randomUUID());
    }
}
