package nl.suriani.java17.sparks.of.design.match.infra;

import nl.suriani.java17.sparks.of.design.match.domain.Match;
import nl.suriani.java17.sparks.of.design.match.domain.MatchID;
import nl.suriani.java17.sparks.of.design.match.domain.Matches;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class MatchRepositoryStub implements MatchRepository {
    private final Map<UUID, MatchEntity> matchDb = new ConcurrentHashMap<>();

    private final MatchToMatchEntityFunction matchToMatchEntity = new MatchToMatchEntityFunction();

    private final MatchEntityToMatchFunction matchEntityToMatch = new MatchEntityToMatchFunction();

    @Override
    public List<Match> findAll() {
        return matchDb.values().stream()
                .map(matchEntityToMatch)
                .toList();
    }

    @Override
    public Optional<Match> findById(MatchID id) {
        return matchDb.values().stream()
                .filter(entity -> entity.getUuid().equals(id.value()))
                .map(matchEntityToMatch)
                .findAny();
    }

    @Override
    public void save(Match match) {
        if (Matches.of(match).is(Match.NoMatch.class)) {
            throw new IllegalStateException("Cannot save a match that doesn't exist.");
        }

        var entity = matchToMatchEntity.apply(match);
        matchDb.put(entity.getUuid(), entity);
    }

}
