package nl.suriani.java17.sparks.of.design.match.infra;

public class MatchService {
    private final MatchRepository matchRepository;

    public MatchService(MatchRepository matchRepository) {
        this.matchRepository = matchRepository;
    }

    public void addNewMatch(AddNewMatchCommand command) {
    }
}
