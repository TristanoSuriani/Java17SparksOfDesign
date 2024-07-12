package nl.suriani.java17.sparks.of.design.inventory.entities;

import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

public record ItemInfo(Brand brand, ItemName name, ItemCategory category,
                       ItemSubcategory subcategory, ItemDescription description) {

    public ItemInfo {
        Guards.isNotNull(name);
        Guards.isNotNull(category);
        Guards.isNotNull(subcategory);
        Guards.isNotNull(description);
    }
}
