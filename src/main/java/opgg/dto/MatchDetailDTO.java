package opgg.dto;
public class MatchDetailDTO {
    private String matchId;
    private String gameMode;
    private int queueId;
    private long gameDuration;
    private String championName;
    private int kills;
    private int deaths;
    private int assists;
    private boolean win;
    private String summonerName;
    private int champLevel;
    private int goldEarned;
    private int visionScore;
    private int totalMinionsKilled;
    private String individualPosition;
    private long gameCreation;

    // --- 생성자 ---
    public MatchDetailDTO() {}

    // --- Getter/Setter ---
    public String getMatchId() {
        return matchId;
    }
    

    public long getGameCreation() {
		return gameCreation;
	}

	public void setGameCreation(long gameCreation) {
		this.gameCreation = gameCreation;
	}

	public int getQueueId() {
		return queueId;
	}

	public void setQueueId(int queueId) {
		this.queueId = queueId;
	}

	public int getChampLevel() {
		return champLevel;
	}

	public void setChampLevel(int champLevel) {
		this.champLevel = champLevel;
	}

	public int getGoldEarned() {
		return goldEarned;
	}

	public void setGoldEarned(int goldEarned) {
		this.goldEarned = goldEarned;
	}

	public int getVisionScore() {
		return visionScore;
	}

	public void setVisionScore(int visionScore) {
		this.visionScore = visionScore;
	}

	public int getTotalMinionsKilled() {
		return totalMinionsKilled;
	}

	public void setTotalMinionsKilled(int totalMinionsKilled) {
		this.totalMinionsKilled = totalMinionsKilled;
	}

	public String getIndividualPosition() {
		return individualPosition;
	}

	public void setIndividualPosition(String individualPosition) {
		this.individualPosition = individualPosition;
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

    public long getGameDuration() {
        return gameDuration;
    }

    public void setGameDuration(long gameDuration) {
        this.gameDuration = gameDuration;
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

    public String getSummonerName() {
        return summonerName;
    }

    public void setSummonerName(String summonerName) {
        this.summonerName = summonerName;
    }
}

