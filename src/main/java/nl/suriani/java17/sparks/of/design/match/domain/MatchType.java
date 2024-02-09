package nl.suriani.java17.sparks.of.design.match.domain;

import java.util.Optional;

public class MatchType {

    public static MatchTypeInspector of(Match match) {
        return new MatchTypeInspector(match);
    }

    public record MatchTypeInspector(Match match) {

        public <T extends Match> T as(Class<T> matchClass) {
            return matchClass.cast(match());
        }

        public <T extends Match> boolean is(Class<T> matchClass) {
            return matchClass.isInstance(match());
        }

        public <T extends Match> Optional<T> possiblyAs(Class<T> matchClass) {
            return Optional.of(matchClass)
                    .filter(this::is)
                    .map(this::as);
        }

        // syntax sugar

        public boolean isNoMatch() {
            return is(Match.NoMatch.class);
        }

        public boolean isNew() {
            return is(Match.New.class);
        }

        public boolean isCancelled() {
            return is(Match.Cancelled.class);
        }

        public boolean isInprogress() {
            return is(Match.InProgress.class);
        }

        public boolean isSuspended() {
            return is(Match.Suspended.class);
        }

        public boolean isTerminated() {
            return is(Match.Terminated.class);
        }

        public Match.NoMatch asNoMatch() {
            return as(Match.NoMatch.class);
        }

        public Match.New asNew() {
            return as(Match.New.class);
        }

        public Match.Cancelled asCancelled() {
            return as(Match.Cancelled.class);
        }

        public Match.InProgress asInProgress() {
            return as(Match.InProgress.class);
        }

        public Match.Suspended asSuspended() {
            return as(Match.Suspended.class);
        }

        public Match.Terminated asTerminated() {
            return as(Match.Terminated.class);
        }

        public Optional<Match.NoMatch> possiblyAsNoMatch() {
            return possiblyAs(Match.NoMatch.class);
        }

        public Optional<Match.New> possiblyAsNew() {
            return possiblyAs(Match.New.class);
        }

        public Optional<Match.Cancelled> possiblyAsCancelled() {
            return possiblyAs(Match.Cancelled.class);
        }

        public Optional<Match.InProgress> possiblyAsInProgress() {
            return possiblyAs(Match.InProgress.class);
        }

        public Optional<Match.Suspended> possiblyAsSuspended() {
            return possiblyAs(Match.Suspended.class);
        }

        public Optional<Match.Terminated> possiblyAsTerminated() {
            return possiblyAs(Match.Terminated.class);
        }
    }
}
