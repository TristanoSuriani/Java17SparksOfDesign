package nl.suriani.java17.sparks.of.design.match.infra;

import nl.suriani.java17.sparks.of.design.match.domain.*;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class MatchRepositoryStubTest {
    @Test
    void test() {
        var matchRepository = new MatchRepositoryStub();
        assertTrue(matchRepository.findAll().isEmpty());

        var match = new Match.Suspended(
                new MatchID(UUID.randomUUID()),
                LocalDateTime.now(),
                new MatchName("Ajax vs Feyenoord"),
                new HomeTeamID(UUID.randomUUID()),
                new AwayTeamID(UUID.randomUUID()),
                new Score(1, 1)
        );

        matchRepository.save(match);

        assertFalse(matchRepository.findAll().isEmpty());

        var savedMatch = matchRepository.findById(match.matchID());
        assertTrue(savedMatch.isPresent());
        assertTrue(MatchType.of(savedMatch.get()).isSuspended());
    }
}