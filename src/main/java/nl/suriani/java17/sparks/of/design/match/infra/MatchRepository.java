package nl.suriani.java17.sparks.of.design.match.infra;

import nl.suriani.java17.sparks.of.design.match.domain.Match;
import nl.suriani.java17.sparks.of.design.match.domain.MatchID;

import java.util.List;

public interface MatchRepository {
    List<Match> findAll();

    Match findById(MatchID id);

    void save(Match match);
}
