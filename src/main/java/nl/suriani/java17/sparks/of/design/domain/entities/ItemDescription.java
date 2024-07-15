package nl.suriani.java17.sparks.of.design.domain.entities;

import nl.suriani.java17.sparks.of.design.domain.validation.Guards;

public record ItemDescription(String value) {
    public ItemDescription {
        Guards.isNotNull(value);
    }
}
