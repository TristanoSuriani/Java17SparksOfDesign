package nl.suriani.java17.sparks.of.design.inventory.entities;

import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

public record ItemName(String value) {
    public ItemName {
        Guards.isNotNull(value);
    }
}
