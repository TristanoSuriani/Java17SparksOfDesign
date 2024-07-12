package nl.suriani.java17.sparks.of.design.inventory.entities;

import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

public record StockAmount(int value) {
    public StockAmount {
        Guards.isBiggerThan0(value);
    }
}
