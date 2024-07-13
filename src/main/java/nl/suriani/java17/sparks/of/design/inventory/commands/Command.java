package nl.suriani.java17.sparks.of.design.inventory.commands;

import nl.suriani.java17.sparks.of.design.inventory.entities.ItemId;
import nl.suriani.java17.sparks.of.design.inventory.entities.ItemInfo;
import nl.suriani.java17.sparks.of.design.inventory.entities.StockAmount;
import nl.suriani.java17.sparks.of.design.inventory.events.Event;
import nl.suriani.java17.sparks.of.design.inventory.validation.Guards;

public sealed interface Command {

    ItemId id();

    record AddItemWithoutStock(ItemId id, ItemInfo info) implements Command {

        public AddItemWithoutStock {
            Guards.isNotNull(id);
            Guards.isNotNull(info);
        }
    }

    record AddItemWithStock(ItemId id, ItemInfo info, StockAmount amount) implements Command {

        public AddItemWithStock {
            Guards.isNotNull(id);
            Guards.isNotNull(info);
            Guards.isNotNull(amount);
        }
    }

    record RemoveItem(ItemId id) implements Command {

        public RemoveItem {
            Guards.isNotNull(id);
        }
    }

    record IncreaseStock(ItemId id, StockAmount amount) implements Command {

        public IncreaseStock {
            Guards.isNotNull(id);
            Guards.isNotNull(amount);
        }
    }

    record DecreaseStock(ItemId id, StockAmount amount) implements Command {

        public DecreaseStock {
            Guards.isNotNull(id);
            Guards.isNotNull(amount);
        }
    }

    default <T extends Command> T as(Class<T> clazz) {
        return clazz.cast(this);
    }
}
