package nl.suriani.java17.sparks.of.design.match.infra;

import java.time.LocalDateTime;
import java.util.UUID;

public class MatchEntity {
    private UUID uuid;
    private String name;
    private LocalDateTime localDateTime;
    private UUID homeTeamUuid;
    private UUID awayTeamUuid;
    private int scoreHomeTeam;
    private int scoreAwayTeam;
    private MatchStatus status;

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public UUID getHomeTeamUuid() {
        return homeTeamUuid;
    }

    public void setHomeTeamUuid(UUID homeTeamUuid) {
        this.homeTeamUuid = homeTeamUuid;
    }

    public UUID getAwayTeamUuid() {
        return awayTeamUuid;
    }

    public void setAwayTeamUuid(UUID awayTeamUuid) {
        this.awayTeamUuid = awayTeamUuid;
    }

    public int getScoreHomeTeam() {
        return scoreHomeTeam;
    }

    public void setScoreHomeTeam(int scoreHomeTeam) {
        this.scoreHomeTeam = scoreHomeTeam;
    }

    public int getScoreAwayTeam() {
        return scoreAwayTeam;
    }

    public void setScoreAwayTeam(int scoreAwayTeam) {
        this.scoreAwayTeam = scoreAwayTeam;
    }

    public MatchStatus getStatus() {
        return status;
    }

    public void setStatus(MatchStatus status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
