package nl.suriani.java17.sparks.of.design.match.infra;

import nl.suriani.java17.sparks.of.design.match.domain.*;

import java.util.function.Function;

public class MatchEntityToMatchFunction implements Function<MatchEntity, Match> {
    @Override
    public Match apply(MatchEntity entity) {
        return switch (entity.getStatus()) {
            case NEW -> fromNewMatch(entity);
            case IN_PROGRESS -> fromMatchInProgress(entity);
            case CANCELLED -> fromCancelled(entity);
            case SUSPENDED -> fromSuspended(entity);
            case TERMINATED -> fromTerminated(entity);
        };
    }

    public Match.New fromNewMatch(MatchEntity entity) {
        return new Match.New(
                new MatchID(entity.getUuid()),
                entity.getLocalDateTime(),
                new MatchName(entity.getName()),
                new HomeTeamID(entity.getHomeTeamUuid()),
                new AwayTeamID(entity.getAwayTeamUuid())
        );
    }

    public Match.InProgress fromMatchInProgress(MatchEntity entity) {
        return new Match.InProgress(
                new MatchID(entity.getUuid()),
                entity.getLocalDateTime(),
                new MatchName(entity.getName()),
                new HomeTeamID(entity.getHomeTeamUuid()),
                new AwayTeamID(entity.getAwayTeamUuid()),
                new Score(entity.getScoreHomeTeam(), entity.getScoreAwayTeam())
        );
    }

    public Match.Cancelled fromCancelled(MatchEntity entity) {
        return new Match.Cancelled(
                new MatchID(entity.getUuid()),
                entity.getLocalDateTime(),
                new MatchName(entity.getName()),
                new HomeTeamID(entity.getHomeTeamUuid()),
                new AwayTeamID(entity.getAwayTeamUuid())
        );
    }

    public Match.Suspended fromSuspended(MatchEntity entity) {
        return new Match.Suspended(
                new MatchID(entity.getUuid()),
                entity.getLocalDateTime(),
                new MatchName(entity.getName()),
                new HomeTeamID(entity.getHomeTeamUuid()),
                new AwayTeamID(entity.getAwayTeamUuid()),
                new Score(entity.getScoreHomeTeam(), entity.getScoreAwayTeam())
        );
    }

    public Match.Terminated fromTerminated(MatchEntity entity) {
        return new Match.Terminated(
                new MatchID(entity.getUuid()),
                entity.getLocalDateTime(),
                new MatchName(entity.getName()),
                new HomeTeamID(entity.getHomeTeamUuid()),
                new AwayTeamID(entity.getAwayTeamUuid()),
                new Score(entity.getScoreHomeTeam(), entity.getScoreAwayTeam())
        );
    }
}
