package nl.suriani.java21.sparks.of.design.domain;

import nl.suriani.java21.sparks.of.design.domain.events.Event;
import nl.suriani.java21.sparks.of.design.domain.entities.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class InventoryItemTest {
    @Test
    void testProjection() {
        var id = new ItemId();
        var info = new ItemInfo(new Brand("Philips"), new ItemName("Lighbulb plus"),
                new ItemCategory("Lightbulbs"), new ItemSubcategory("Electric equipment"),
                new ItemDescription("A lightbulb."));

        var events = List.<Event>of(
                new Event.ItemAddedWithStock(id, LocalDateTime.now(), info, new StockAmount(100)),
                new Event.StockDecreased(id, LocalDateTime.now(), new StockAmount(99)),
                new Event.PutOutOfStock(id, LocalDateTime.now()),
                new Event.StockIncreased(id, LocalDateTime.now(), new StockAmount(100)),
                new Event.ItemRemoved()
        );

        var state = InventoryItem.project(events);

        System.out.println(state);
    }

}