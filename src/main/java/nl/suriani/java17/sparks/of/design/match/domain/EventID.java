package nl.suriani.java17.sparks.of.design.match.domain;


import java.util.UUID;

public class EventID extends Value<UUID> {
    public EventID(UUID value) {
        super(value);
    }

    public EventID() {
        super(UUID.randomUUID());
    }
}
