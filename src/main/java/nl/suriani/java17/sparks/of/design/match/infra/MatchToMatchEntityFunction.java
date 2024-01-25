package nl.suriani.java17.sparks.of.design.match.infra;

import nl.suriani.java17.sparks.of.design.match.domain.Match;
import nl.suriani.java17.sparks.of.design.match.domain.MatchType;

import java.util.Optional;
import java.util.function.Function;

public class MatchToMatchEntityFunction implements Function<Match, MatchEntity> {
    @Override
    public MatchEntity apply(Match match) {
        return map()
                .or(() -> whenNew(match)
                        .map(this::fromNewMatch))

                .or(() -> whenInProgress(match)
                        .map(this::fromMatchInProgress))

                .or(() -> whenCancelled(match)
                        .map(this::fromCancelled))

                .or(() -> whenSuspended(match)
                        .map(this::fromSuspended))

                .or(() -> whenTerminated(match)
                        .map(this::fromTerminated))

                .orElseThrow();
    }

    private static Optional<Match.New> whenNew(Match match) {
        return MatchType.of(match)
                .possiblyAsNew();
    }

    private static Optional<Match.InProgress> whenInProgress(Match match) {
        return MatchType.of(match)
                .possiblyAsInProgress();
    }

    private static Optional<Match.Cancelled> whenCancelled(Match match) {
        return MatchType.of(match)
                .possiblyAsCancelled();
    }

    private static Optional<Match.Suspended> whenSuspended(Match match) {
        return MatchType.of(match)
                .possiblyAsSuspended();
    }

    private static Optional<Match.Terminated> whenTerminated(Match match) {
        return MatchType.of(match)
                .possiblyAsTerminated();
    }

    private MatchEntity fromNewMatch(Match.New match) {
        var entity = new MatchEntity();
        entity.setUuid(match.matchID().value());
        entity.setName(match.name().value());
        entity.setLocalDateTime(match.dateTime());
        entity.setHomeTeamUuid(match.homeTeamID().value());
        entity.setAwayTeamUuid(match.awayTeamID().value());
        entity.setScoreHomeTeam(0);
        entity.setScoreAwayTeam(0);
        entity.setStatus(MatchStatus.NEW);
        return entity;
    }

    private MatchEntity fromMatchInProgress(Match.InProgress match) {
        var entity = new MatchEntity();
        entity.setUuid(match.matchID().value());
        entity.setName(match.name().value());
        entity.setLocalDateTime(match.dateTime());
        entity.setHomeTeamUuid(match.homeTeamID().value());
        entity.setAwayTeamUuid(match.awayTeamID().value());
        entity.setScoreHomeTeam(match.score().scoreHomeTeam());
        entity.setScoreAwayTeam(match.score().scoreAwayTeam());
        entity.setStatus(MatchStatus.IN_PROGRESS);
        return entity;
    }

    private MatchEntity fromCancelled(Match.Cancelled match) {
        var entity = new MatchEntity();
        entity.setUuid(match.matchID().value());
        entity.setName(match.name().value());
        entity.setLocalDateTime(match.dateTime());
        entity.setHomeTeamUuid(match.homeTeamID().value());
        entity.setAwayTeamUuid(match.awayTeamID().value());
        entity.setStatus(MatchStatus.CANCELLED);
        return entity;
    }

    private MatchEntity fromSuspended(Match.Suspended match) {
        var entity = new MatchEntity();
        entity.setUuid(match.matchID().value());
        entity.setName(match.name().value());
        entity.setLocalDateTime(match.dateTime());
        entity.setHomeTeamUuid(match.homeTeamID().value());
        entity.setAwayTeamUuid(match.awayTeamID().value());
        entity.setScoreHomeTeam(match.score().scoreHomeTeam());
        entity.setScoreAwayTeam(match.score().scoreAwayTeam());
        entity.setStatus(MatchStatus.SUSPENDED);
        return entity;
    }

    private MatchEntity fromTerminated(Match.Terminated match) {
        var entity = new MatchEntity();
        entity.setUuid(match.matchID().value());
        entity.setName(match.name().value());
        entity.setLocalDateTime(match.dateTime());
        entity.setHomeTeamUuid(match.homeTeamID().value());
        entity.setAwayTeamUuid(match.awayTeamID().value());
        entity.setScoreHomeTeam(match.score().scoreHomeTeam());
        entity.setScoreAwayTeam(match.score().scoreAwayTeam());
        entity.setStatus(MatchStatus.TERMINATED);
        return entity;
    }

    private Optional<MatchEntity> map() {
        return Optional.empty();
    }
}
