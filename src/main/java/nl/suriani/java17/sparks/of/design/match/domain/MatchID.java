package nl.suriani.java17.sparks.of.design.match.domain;


import java.util.UUID;

public class MatchID extends Value<UUID> {
    public MatchID(UUID value) {
        super(value);
    }

    public MatchID() {
        super(UUID.randomUUID());
    }
}
