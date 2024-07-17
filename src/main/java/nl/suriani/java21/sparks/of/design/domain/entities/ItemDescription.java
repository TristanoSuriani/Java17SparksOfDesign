package nl.suriani.java21.sparks.of.design.domain.entities;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record ItemDescription(String value) {
    public ItemDescription {
        Guards.isNotNull(value);
    }
}
