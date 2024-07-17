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
        // fetch events
        // project state
        // decide new events
        // evolve state
        // publish events
        // save state

        var events = eventStore.findById(command.id().value().toString());
        var state = InventoryItem.project(events);

        var newEvents = decider.apply(command, state);
        state = state.evolve(newEvents);

        newEvents.forEach(eventStore::publish);
        repository.save(state);
    }
}
