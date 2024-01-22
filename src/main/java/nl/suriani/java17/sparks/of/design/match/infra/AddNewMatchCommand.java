package nl.suriani.java17.sparks.of.design.match.infra;

import nl.suriani.java17.sparks.of.design.match.domain.AwayTeamID;
import nl.suriani.java17.sparks.of.design.match.domain.HomeTeamID;
import nl.suriani.java17.sparks.of.design.match.domain.MatchID;
import nl.suriani.java17.sparks.of.design.match.domain.MatchName;

import java.time.LocalDateTime;

public record AddNewMatchCommand(MatchID matchID,
                                 LocalDateTime dateTime,
                                 MatchName name,
                                 HomeTeamID homeTeamID,
                                 AwayTeamID awayTeamID) {
}
