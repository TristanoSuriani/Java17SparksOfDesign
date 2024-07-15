package nl.suriani.java17.sparks.of.design.inventory;

import nl.suriani.java17.sparks.of.design.inventory.entities.*;
import nl.suriani.java17.sparks.of.design.inventory.events.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryItemTest {
    @Test
    void testProjection() {
        var id = new ItemId();
        var info = new ItemInfo(new Brand("Philips"), new ItemName("Lighbulb plus"),
                new ItemCategory("Lightbulbs"), new ItemSubcategory("Electric equipment"),
                new ItemDescription("A lightbulb."));

        var events = List.<Event>of(
                new Event.ItemAddedWithStock(id, LocalDateTime.now(), info, new StockAmount(100)),
                new Event.StockDecreased(new StockAmount(99))
        );

        var state = InventoryItem.project(events);

        System.out.println(state);
    }

}