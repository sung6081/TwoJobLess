package opgg.dto;

import java.util.List;

public class MatchDetailDTO {
    private String matchId;
    private String gameMode;
    private int queueId;
    private long gameDuration;
    private long gameCreation;

    private String championName;
    private int kills;
    private int deaths;
    private int assists;
    private boolean win;

    private List<ParticipantDetailDTO> participants;

    // --- Getters & Setters ---
    public String getMatchId() {
        return matchId;
    }
    public void setMatchId(String matchId) {
        this.matchId = matchId;
    }

    public String getGameMode() {
        return gameMode;
    }
    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public int getQueueId() {
        return queueId;
    }
    public void setQueueId(int queueId) {
        this.queueId = queueId;
    }

    public long getGameDuration() {
        return gameDuration;
    }
    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
    }

    public long getGameCreation() {
        return gameCreation;
    }
    public void setGameCreation(long gameCreation) {
        this.gameCreation = gameCreation;
    }

    public String getChampionName() {
        return championName;
    }
    public void setChampionName(String championName) {
        this.championName = championName;
    }

    public int getKills() {
        return kills;
    }
    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }
    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getAssists() {
        return assists;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }

    public boolean isWin() {
        return win;
    }
    public void setWin(boolean win) {
        this.win = win;
    }

    public List<ParticipantDetailDTO> getParticipants() {
        return participants;
    }
    public void setParticipants(List<ParticipantDetailDTO> participants) {
        this.participants = participants;
    }
}
