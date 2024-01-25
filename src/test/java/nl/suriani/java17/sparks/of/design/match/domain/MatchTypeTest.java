package nl.suriani.java17.sparks.of.design.match.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MatchTypeTest {
    @Test
    void exampleUsage() {
        Match match = new Match.New(
                new MatchID(),
                LocalDateTime.now(),
                new MatchName(),
                new HomeTeamID(UUID.randomUUID()),
                new AwayTeamID(UUID.randomUUID())
            )
            .setInProgress();

        assertTrue(MatchType.of(match).is(Match.InProgress.class));
        assertDoesNotThrow(() -> MatchType.of(match).as(Match.InProgress.class));
        assertThrows(ClassCastException.class, () -> MatchType.of(match).as(Match.Terminated.class));

        var maybeMatchInProgress = MatchType.of(match)
                .possiblyAs(Match.InProgress.class);

        assertTrue(maybeMatchInProgress.isPresent());

        var maybeMatchTerminated = MatchType.of(match)
                .possiblyAs(Match.Terminated.class);

        assertTrue(maybeMatchTerminated.isEmpty());
    }

    @Test
    void exampleUsage2() {
        var match = getAMatch();

        MatchType.of(match)
                .possiblyAs(Match.InProgress.class)
                .ifPresentOrElse(
                        (m) -> System.out.println("match is in progress"),
                        () -> System.out.println("there is no match that can be set in progress")
                );
    }

    private Match getAMatch() {
        return new Match.NoMatch();
    }
}