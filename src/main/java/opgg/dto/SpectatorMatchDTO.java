package opgg.dto;

import java.util.List;

public class SpectatorMatchDTO {

	//Field
	private long gameId; //게임 식별번호
	private long mapId; //맵
	private String gameMode; //게임 모드
	private String gameType; //게임 타입
	private long gameQueueConfigId;
	private List<ParticipantOfSpectatorDTO> participants; //참가자들 정보
	private long gameStartTime; //겜 시작 시간
	private long gameLength; //겜 시간(초)
	
	//Constructor
	public SpectatorMatchDTO() {
		
	}

	public SpectatorMatchDTO(long gameId, long mapId, String gameMode, String gameType, long gameQueueConfigId,
			List<ParticipantOfSpectatorDTO> participants, long gameStartTime, long gameLength) {
		super();
		this.gameId = gameId;
		this.mapId = mapId;
		this.gameMode = gameMode;
		this.gameType = gameType;
		this.gameQueueConfigId = gameQueueConfigId;
		this.participants = participants;
		this.gameStartTime = gameStartTime;
		this.gameLength = gameLength;
	}

	//Getter & Setter
	public long getGameId() {
		return gameId;
	}

	public void setGameId(long gameId) {
		this.gameId = gameId;
	}

	public long getMapId() {
		return mapId;
	}

	public void setMapId(long mapId) {
		this.mapId = mapId;
	}

	public String getGameMode() {
		return gameMode;
	}

	public void setGameMode(String gameMode) {
		this.gameMode = gameMode;
	}

	public String getGameType() {
		return gameType;
	}

	public void setGameType(String gameType) {
		this.gameType = gameType;
	}

	public long getGameQueueConfigId() {
		return gameQueueConfigId;
	}

	public void setGameQueueConfigId(long gameQueueConfigId) {
		this.gameQueueConfigId = gameQueueConfigId;
	}

	public List<ParticipantOfSpectatorDTO> getParticipants() {
		return participants;
	}

	public void setParticipants(List<ParticipantOfSpectatorDTO> participants) {
		this.participants = participants;
	}

	public long getGameStartTime() {
		return gameStartTime;
	}

	public void setGameStartTime(long gameStartTime) {
		this.gameStartTime = gameStartTime;
	}

	public long getGameLength() {
		return gameLength;
	}

	public void setGameLength(long gameLength) {
		this.gameLength = gameLength;
	}

	@Override
	public String toString() {
		return "SpectatorMatchDTO [gameId=" + gameId + ", mapId=" + mapId + ", gameMode=" + gameMode + ", gameType="
				+ gameType + ", gameQueueConfigId=" + gameQueueConfigId + ",\n participants=" + participants
				+ ", gameStartTime=" + gameStartTime + ", gameLength=" + gameLength + "]";
	}
	
}
