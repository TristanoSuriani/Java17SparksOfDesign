package nl.suriani.java17.sparks.of.design.match.domain;


import java.util.List;

public record PlayerEvent(EventID eventID,
                          PlayerID playerID,
                          PlayerName playerName,
                          EventType eventType,
                          Minute minute,
                          List<Note> notes) {

    public PlayerEvent {
        Guard.areNotNull(eventID, playerID, playerName, eventType, minute, notes);
    }
}
