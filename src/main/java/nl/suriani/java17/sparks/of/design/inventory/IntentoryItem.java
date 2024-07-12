package nl.suriani.java17.sparks.of.design.inventory;

public sealed interface IntentoryItem permits InStock, NoItem, OutOfStock {
}
