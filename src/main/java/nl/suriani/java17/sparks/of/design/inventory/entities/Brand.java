package nl.suriani.java17.sparks.of.design.inventory.entities;

import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

public record Brand(String value) {
    public Brand {
        Guards.isNotNull(value);
    }
}
