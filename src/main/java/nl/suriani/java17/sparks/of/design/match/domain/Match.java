package nl.suriani.java17.sparks.of.design.match.domain;

import java.time.LocalDateTime;

public sealed interface Match {

    record NoMatch() implements Match {
        public New newMatch(MatchID matchID,
                        LocalDateTime dateTime,
                        MatchName name,
                        HomeTeamID homeTeamID,
                        AwayTeamID awayTeamID) {

            return new New(matchID,
                    dateTime,
                    name,
                    homeTeamID,
                    awayTeamID);
        }
    }

    record New(MatchID matchID,
               LocalDateTime dateTime,
               MatchName name,
               HomeTeamID homeTeamID,
               AwayTeamID awayTeamID) implements Match {

        public New {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID);
        }

        public Cancelled cancel() {
            return new Cancelled(matchID, dateTime, name, homeTeamID, awayTeamID);
        }

        public InProgress setInProgress() {
            return new InProgress(matchID, dateTime, name, homeTeamID, awayTeamID, new Score());
        }
    }

    record Cancelled(MatchID matchID,
                     LocalDateTime dateTime,
                     MatchName name,
                     HomeTeamID homeTeamID,
                     AwayTeamID awayTeamID) implements Match {

        public Cancelled {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID);
        }

        public New revertToNewMatch() {
            return new New(matchID, dateTime, name, homeTeamID, awayTeamID);
        }
    }

    record InProgress(MatchID matchID,
                      LocalDateTime dateTime,
                      MatchName name,
                      HomeTeamID homeTeamID,
                      AwayTeamID awayTeamID,
                      Score score) implements Match {

        public InProgress {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }

        public Suspended suspend() {
            return new Suspended(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }

        public Terminated terminate() {
            return new Terminated(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }
    }

    record Suspended(MatchID matchID,
                     LocalDateTime dateTime,
                     MatchName name,
                     HomeTeamID homeTeamID,
                     AwayTeamID awayTeamID,
                     Score score) implements Match {

        public Suspended {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }

        public InProgress revertToMatchInProgress() {
            return new InProgress(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }
    }

    record Terminated(MatchID matchID,
                      LocalDateTime dateTime,
                      MatchName name,
                      HomeTeamID homeTeamID,
                      AwayTeamID awayTeamID,
                      Score score) implements Match {

        public Terminated {
            Guard.areNotNull(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }

        public InProgress revertToMatchInProgress() {
            return new InProgress(matchID, dateTime, name, homeTeamID, awayTeamID, score);
        }
    }
}

