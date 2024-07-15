package nl.suriani.java17.sparks.of.design.application;

import nl.suriani.java17.sparks.of.design.domain.*;
import nl.suriani.java17.sparks.of.design.domain.commands.Command;
import nl.suriani.java17.sparks.of.design.domain.commands.Decide;
import nl.suriani.java17.sparks.of.design.domain.events.Event;

import java.time.LocalDateTime;
import java.util.List;

public class DecreaseStockDecider implements Decide<Command.DecreaseStock, InventoryItem, Event> {

    @Override
    public List<Event> apply(Command.DecreaseStock command, InventoryItem inventoryItem) {
        return switch (inventoryItem) {
            case InStock inStock -> {
                var difference = inStock.amount().value() - command.amount().value();
                if (difference < 0) {
                    throw new IllegalArgumentException("The stock is insufficient to fullfill the order.");
                }

                if (difference == 0) {
                    yield List.of(new Event.PutOutOfStock(inStock.id(), LocalDateTime.now()));
                }

                yield List.of(new Event.StockDecreased(command.amount()));
            }

            default -> throw new IllegalArgumentException("Cannot decrease stock.");
        };
    }
}