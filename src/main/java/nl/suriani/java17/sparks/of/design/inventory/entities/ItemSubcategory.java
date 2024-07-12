package nl.suriani.java17.sparks.of.design.inventory.entities;

import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

public record ItemSubcategory(String value) {
    public ItemSubcategory {
        Guards.isNotNull(value);
    }
}
