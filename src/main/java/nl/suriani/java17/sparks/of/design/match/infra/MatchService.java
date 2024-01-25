package nl.suriani.java17.sparks.of.design.match.infra;

import nl.suriani.java17.sparks.of.design.match.domain.Match;
import nl.suriani.java17.sparks.of.design.match.domain.MatchID;
import nl.suriani.java17.sparks.of.design.match.domain.MatchType;

public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void addNewMatch(AddNewMatchCommand command) {
        var existingMatch = matchRepository.findById(command.matchID());
        var matchType = MatchType.of(existingMatch);

        if (!matchType.isNoMatch()) {
            throw new IllegalStateException("This match exists already.");
        }

        var match = new Match.New(
                command.matchID(),
                command.dateTime(),
                command.name(),
                command.homeTeamID(),
                command.awayTeamID());

        matchRepository.save(match);
    }

    public void setMatchInProgress(MatchID matchID) {
        var existingMatch = matchRepository.findById(matchID);
        var match = setInProgressIfPossible(existingMatch);
        matchRepository.save(match);
    }

    public Match setInProgressIfPossible(Match match) {
        var matchType = MatchType.of(match);

        if (matchType.isNew()) {
            return matchType.asNew().setInProgress();
        }

        if (matchType.isSuspended()) {
            return matchType.asSuspended().revertToMatchInProgress();
        }

        if (matchType.isTerminated()) {
            return matchType.asTerminated().revertToMatchInProgress();
        }

        throw new IllegalStateException("Impossible to set match in progress. Found state: " + match.getClass().getSimpleName());
    }
}
