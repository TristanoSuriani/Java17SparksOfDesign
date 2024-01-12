package nl.suriani.java17.sparks.of.design.match.domain;

public sealed interface MatchEvent {
    record NewMatchCreated(Match match) implements MatchEvent {}
    record MatchStarted(MatchID matchID) implements MatchEvent {}
    record StartedMatchRevertedToNew(MatchID matchID) implements MatchEvent {}
    record YellowCardAssigned() implements MatchEvent {}
    record RedCardAssigned() implements MatchEvent {}
    record GoalScored() implements MatchEvent {}
    record InjuryOccurred() implements MatchEvent {}
    record MatchTerminated(MatchID matchID) implements MatchEvent {}
}
