package nl.suriani.java17.sparks.of.design.match.infra;

import nl.suriani.java17.sparks.of.design.match.domain.Match;
import nl.suriani.java17.sparks.of.design.match.domain.MatchID;

import java.util.List;
import java.util.Optional;

public interface MatchRepository {
    List<Match> findAll();

    Optional<Match> findById(MatchID id);

    void save(Match match);
}
