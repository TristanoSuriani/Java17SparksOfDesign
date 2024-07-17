package nl.suriani.java21.sparks.of.design.domain.entities;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record ItemSubcategory(String value) {
    public ItemSubcategory {
        Guards.isNotNull(value);
    }
}
