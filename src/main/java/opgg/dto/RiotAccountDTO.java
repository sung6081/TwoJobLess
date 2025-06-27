package opgg.dto;

import opgg.entity.RiotAccount;

public class RiotAccountDTO {
	
	//Field
	private String gameName;
	private String tagLine;
	private String puuid;
	private long profileIconId;
	private long summonerLevel;
	
	//Constuctor
	public RiotAccountDTO() {
		
	}
	
	public RiotAccountDTO(RiotAccount riotAccount) {
		
		this.gameName = riotAccount.getGameName();
		this.tagLine = riotAccount.getTagLine();
		this.puuid = riotAccount.getPuuid();
		
	}

	public RiotAccountDTO(String gameName, String tagLine, String puuid, long profileIconId, long summonerLevel) {
		this.gameName = gameName;
		this.tagLine = tagLine;
		this.puuid = puuid;
		this.profileIconId = profileIconId;
		this.summonerLevel = summonerLevel;
	}

	//Getter & Setter
	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getTagLine() {
		return tagLine;
	}

	public void setTagLine(String tagLine) {
		this.tagLine = tagLine;
	}

	public String getPuuid() {
		return puuid;
	}

	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}

	public long getProfileIconId() {
		return profileIconId;
	}

	public void setProfileIconId(long profileIconId) {
		this.profileIconId = profileIconId;
	}

	public long getSummonerLevel() {
		return summonerLevel;
	}

	public void setSummonerLevel(long summonerLevel) {
		this.summonerLevel = summonerLevel;
	}
	
}
