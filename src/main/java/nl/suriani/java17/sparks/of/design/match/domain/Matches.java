package nl.suriani.java17.sparks.of.design.match.domain;

import java.util.Optional;

public class Matches {

    public static MatchTypeInspector of(Match match) {
        return new MatchTypeInspector(match);
    }

    public record MatchTypeInspector(Match match) {
        public Match match() {
            return match;
        }

        public <T extends Match> boolean is(Class<T> matchClass) {
            return matchClass.isAssignableFrom(match().getClass());
        }

        public <T extends Match> T as(Class<T> matchClass) {
            return matchClass.cast(match());
        }

        public <T extends Match> Optional<T> possiblyAs(Class<T> matchClass) {
            return Optional.of(matchClass)
                    .filter(this::is)
                    .map(this::as);
        }
    }
}
