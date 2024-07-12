package nl.suriani.java17.sparks.of.design.inventory.entities;

import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

public record ItemDescription(String value) {
    public ItemDescription {
        Guards.isNotNull(value);
    }
}
