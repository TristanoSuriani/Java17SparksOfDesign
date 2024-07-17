package nl.suriani.java21.sparks.of.design.domain;

import nl.suriani.java21.sparks.of.design.application.DecreaseStockUseCase;
import nl.suriani.java21.sparks.of.design.domain.commands.Command;
import nl.suriani.java21.sparks.of.design.domain.entities.*;
import nl.suriani.java21.sparks.of.design.domain.events.Event;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DecreaseStockUseCaseTest {
    private DecreaseStockUseCase decreaseStockUseCase;

    private static final ItemInfo info = new ItemInfo(new Brand("Philips"), new ItemName("Lightbulb plus"),
                new ItemCategory("Lightbulbs"), new ItemSubcategory("Electric equipment"),
                new ItemDescription("A lightbulb."));

    @Test
    void decreaseStockPartially() {
        var eventStore = new EventStoreStub();
        var inventoryItemRepository = new InventoryItemRepositoryStub();
        var decreaseStockUseCase = new DecreaseStockUseCase(eventStore, inventoryItemRepository);
        var id = new ItemId();
        var events = List.of(new Event.ItemAddedWithStock(id, LocalDateTime.now(), info, new StockAmount(100)));
        events.forEach(eventStore::publish);

        var command = new Command.DecreaseStock(id, new StockAmount(70));

        decreaseStockUseCase.decreaseStock(command);

        var item = inventoryItemRepository.findById(id).orElseThrow();
        var inStock = item.as(InStock.class);

        assertEquals(new StockAmount(30), inStock.amount());
    }

    @Test
    void decreaseStockCompletely() {
        var eventStore = new EventStoreStub();
        var inventoryItemRepository = new InventoryItemRepositoryStub();
        var decreaseStockUseCase = new DecreaseStockUseCase(eventStore, inventoryItemRepository);
        var id = new ItemId();
        var events = List.of(new Event.ItemAddedWithStock(id, LocalDateTime.now(), info, new StockAmount(100)));
        events.forEach(eventStore::publish);

        var command = new Command.DecreaseStock(id, new StockAmount(100));

        decreaseStockUseCase.decreaseStock(command);

        var item = inventoryItemRepository.findById(id).orElseThrow();
        assertTrue(item.is(OutOfStock.class));
    }
}
