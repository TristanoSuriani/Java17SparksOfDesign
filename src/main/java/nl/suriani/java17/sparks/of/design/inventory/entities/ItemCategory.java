package nl.suriani.java17.sparks.of.design.inventory.entities;

import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

public record ItemCategory(String value) {
    public ItemCategory {
        Guards.isNotNull(value);
    }
}
