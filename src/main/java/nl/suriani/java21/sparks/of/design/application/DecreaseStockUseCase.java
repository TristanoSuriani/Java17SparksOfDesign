package nl.suriani.java21.sparks.of.design.application;

import nl.suriani.java21.sparks.of.design.domain.InventoryItem;
import nl.suriani.java21.sparks.of.design.domain.InventoryItemRepository;
import nl.suriani.java21.sparks.of.design.domain.commands.Command;
import nl.suriani.java21.sparks.of.design.domain.events.Event;
import nl.suriani.java21.sparks.of.design.domain.events.EventStore;

public class DecreaseStockUseCase {
    private final EventStore<Event> eventStore;
    private final InventoryItemRepository repository;
    private static final DecreaseStockDecider decider = new DecreaseStockDecider();

    public DecreaseStockUseCase(EventStore<Event> eventStore, InventoryItemRepository repository) {
        this.eventStore = eventStore;
        this.repository = repository;
    }

    public void decreaseStock(Command.DecreaseStock command) {
        var events = eventStore.findById(command.id().value().toString());
        var state = InventoryItem.project(events);

        var nextEvents = decider.apply(command, state);

        for (var nextEvent : nextEvents) {
            state = state.evolve(nextEvent);
        }

        nextEvents.forEach(eventStore::publish);
        repository.save(state);
    }
}
