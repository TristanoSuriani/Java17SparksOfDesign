package nl.suriani.java17.sparks.of.design.domain.entities;

import nl.suriani.java17.sparks.of.design.domain.validation.Guards;

public record StockAmount(int value) {
    public StockAmount {
        Guards.isBiggerThan0(value);
    }

    public StockAmount add(StockAmount other) {
        return new StockAmount(this.value + other.value);
    }

    public StockAmount subtract(StockAmount other) {
        return new StockAmount(this.value - other.value);
    }
}
