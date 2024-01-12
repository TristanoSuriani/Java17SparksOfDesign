package nl.suriani.java17.sparks.of.design.match.domain;


public record Player(PlayerID playerID, PlayerName name, boolean isEligible) {
    public Player {
        Guard.areNotNull(playerID, name);
    }
}
