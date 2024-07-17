package nl.suriani.java21.sparks.of.design.domain.entities;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record Brand(String value) {
    public Brand {
        Guards.isNotNull(value);
    }
}
