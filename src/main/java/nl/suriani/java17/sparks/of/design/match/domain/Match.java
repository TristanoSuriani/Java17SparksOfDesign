package nl.suriani.java17.sparks.of.design.match.domain;

import java.time.LocalDateTime;

public sealed interface Match {

    record NoMatch() implements Match {}

    record NewMatch(MatchID matchID,
                    LocalDateTime dateTime,
                    MatchName name,
                    HomeTeamID homeTeamID,
                    AwayTeamID awayTeamID) implements Match {

        public NewMatch {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID);
        }

        public MatchCancelled cancel() {
            return new MatchCancelled(matchID, dateTime, name, homeTeamID, awayTeamID);
        }

        public MatchInProgress setInProgress() {
            return new MatchInProgress(matchID, dateTime, name, homeTeamID, awayTeamID, new Score());
        }
    }

    record MatchCancelled(MatchID matchID,
                          LocalDateTime dateTime,
                          MatchName name,
                          HomeTeamID homeTeamID,
                          AwayTeamID awayTeamID) implements Match {

        public MatchCancelled {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID);
        }

        public NewMatch revertToNewMatch() {
            return new NewMatch(matchID, dateTime, name, homeTeamID, awayTeamID);
        }
    }

    record MatchInProgress(MatchID matchID,
                           LocalDateTime dateTime,
                           MatchName name,
                           HomeTeamID homeTeamID,
                           AwayTeamID awayTeamID,
                           Score score) implements Match {

        public MatchInProgress {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }

        public MatchSuspended suspend() {
            return new MatchSuspended(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }

        public MatchTerminated terminate() {
            return new MatchTerminated(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }
    }

    record MatchSuspended(MatchID matchID,
                          LocalDateTime dateTime,
                          MatchName name,
                          HomeTeamID homeTeamID,
                          AwayTeamID awayTeamID,
                          Score score) implements Match {

        public MatchSuspended {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }

        public MatchInProgress revertToMatchInProgress() {
            return new MatchInProgress(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }
    }

    record MatchTerminated(MatchID matchID,
                           LocalDateTime dateTime,
                           MatchName name,
                           HomeTeamID homeTeamID,
                           AwayTeamID awayTeamID,
                           Score score) implements Match {

        public MatchTerminated {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }

        public MatchInProgress revertToMatchInProgress() {
            return new MatchInProgress(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }
    }
}

