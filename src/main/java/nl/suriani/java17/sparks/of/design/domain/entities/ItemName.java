package nl.suriani.java17.sparks.of.design.domain.entities;

import nl.suriani.java17.sparks.of.design.domain.validation.Guards;

public record ItemName(String value) {
    public ItemName {
        Guards.isNotNull(value);
    }
}
