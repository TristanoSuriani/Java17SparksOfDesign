package nl.suriani.java17.sparks.of.design.domain.entities;

import nl.suriani.java17.sparks.of.design.domain.validation.Guards;

public record Brand(String value) {
    public Brand {
        Guards.isNotNull(value);
    }
}
