package opgg.dto;

import java.util.List;

public class MatchDetailDTO {
    private String matchId;
    private String gameMode;
    private int queueId;
    private long gameDuration;
    private long gameCreation;

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

    public List<ParticipantDetailDTO> getParticipants() {
        return participants;
    }
    public void setParticipants(List<ParticipantDetailDTO> participants) {
        this.participants = participants;
    }
}
