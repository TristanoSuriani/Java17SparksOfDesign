package nl.suriani.java21.sparks.of.design.domain.entities;

import nl.suriani.java21.sparks.of.design.domain.validation.Guards;

public record ItemInfo(Brand brand, ItemName name, ItemCategory category,
                       ItemSubcategory subcategory, ItemDescription description) {

    public ItemInfo {
        Guards.isNotNull(name);
        Guards.isNotNull(category);
        Guards.isNotNull(subcategory);
        Guards.isNotNull(description);
    }
}
