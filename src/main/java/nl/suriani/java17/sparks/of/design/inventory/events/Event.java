package nl.suriani.java17.sparks.of.design.inventory.events;

import nl.suriani.java17.sparks.of.design.inventory.entities.ItemId;
import nl.suriani.java17.sparks.of.design.inventory.entities.ItemInfo;
import nl.suriani.java17.sparks.of.design.inventory.entities.StockAmount;
import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

import java.time.LocalDateTime;

public sealed interface Event {
    ItemId id();
    LocalDateTime dateTime();
    Version version();
    Type type();
    
    record ItemAddedWithoutStock(ItemId id, LocalDateTime dateTime, ItemInfo info) implements Event {

        public ItemAddedWithoutStock {
            Guards.isNotNull(id);
            Guards.isNotNull(dateTime);
            Guards.isNotNull(info);
        }

        public ItemAddedWithoutStock(ItemInfo info) {
            this(new ItemId(), LocalDateTime.now(), info);
        }

        public Type type() {
            return Type.ITEM_ADDED_WITHOUT_STOCK;
        }

        public Version version() {
            return new Version();
        }
    }

    record ItemAddedWithStock(ItemId id, LocalDateTime dateTime, ItemInfo info, StockAmount amount) implements Event {

        public ItemAddedWithStock {
            Guards.isNotNull(id);
            Guards.isNotNull(dateTime);
            Guards.isNotNull(info);
            Guards.isNotNull(amount);
        }

        public ItemAddedWithStock(ItemInfo info, StockAmount amount) {
            this(new ItemId(), LocalDateTime.now(), info, amount);
        }

        public Type type() {
            return Type.ITEM_ADDED_WITH_STOCK;
        }

        public Version version() {
            return new Version();
        }
    }
    
    record ItemRemoved(ItemId id, LocalDateTime dateTime) implements Event {
        public ItemRemoved {
            Guards.isNotNull(id);
            Guards.isNotNull(dateTime);
        }

        public ItemRemoved() {
            this(new ItemId(), LocalDateTime.now());
        }

        public Type type() {
            return Type.ITEM_REMOVED;
        }

        public Version version() {
            return new Version();
        }
    }

    record StockIncreased(ItemId id, LocalDateTime dateTime, StockAmount amount) implements Event {
        public StockIncreased {
            Guards.isNotNull(id);
            Guards.isNotNull(dateTime);
            Guards.isNotNull(amount);
        }

        public StockIncreased(StockAmount amount) {
            this(new ItemId(), LocalDateTime.now(), amount);
        }

        public Type type() {
            return Type.STOCK_INCREASED;
        }

        public Version version() {
            return new Version();
        }
    }

    record StockDecreased(ItemId id, LocalDateTime dateTime, StockAmount amount) implements Event {
        public StockDecreased {
            Guards.isNotNull(id);
            Guards.isNotNull(dateTime);
            Guards.isNotNull(amount);
        }

        public StockDecreased(StockAmount amount) {
            this(new ItemId(), LocalDateTime.now(), amount);
        }

        public Type type() {
            return Type.ITEM_ADDED_WITHOUT_STOCK;
        }

        public Version version() {
            return new Version();
        }
    }

    record PutOutOfStock(ItemId id, LocalDateTime dateTime) implements Event {
        public PutOutOfStock {
            Guards.isNotNull(id);
            Guards.isNotNull(dateTime);
        }

        public PutOutOfStock() {
            this(new ItemId(), LocalDateTime.now());
        }

        public Type type() {
            return Type.PUT_OUT_OF_STOCK;
        }

        public Version version() {
            return new Version();
        }
    }

    enum Type {
        ITEM_ADDED_WITHOUT_STOCK,
        ITEM_ADDED_WITH_STOCK,
        ITEM_REMOVED,
        STOCK_INCREASED,
        STOCK_DECREASED,
        PUT_OUT_OF_STOCK;
    }

    default <T extends Event> T as(Class<T> clazz) {
        return clazz.cast(this);
    }
}
