package nl.suriani.java17.sparks.of.design.domain.entities;

import nl.suriani.java17.sparks.of.design.domain.validation.Guards;

import java.util.UUID;

public record ItemId(UUID value) {
    public ItemId {
        Guards.isNotNull(value);
    }

    public ItemId() {
        this(UUID.randomUUID());
    }
}
