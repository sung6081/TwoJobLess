package opgg.dto;

import opgg.entity.RiotAccount;

public class RiotAccountDTO {
	
	//Field
	private String gameName;
	private String tagLine;
	private String puuid;
	private long profileIconId;
	private long summonerLevel;
	private String id;
	
	//Constuctor
	public RiotAccountDTO() {
		
	}
	
	public RiotAccountDTO(RiotAccount riotAccount) {
		
		this.gameName = riotAccount.getGameName();
		this.tagLine = riotAccount.getTagLine();
		this.puuid = riotAccount.getPuuid();
		this.profileIconId = riotAccount.getProfileIconId();
		this.summonerLevel = riotAccount.getSummonerLevel();
		
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "RiotAccountDTO [gameName=" + gameName + ", tagLine=" + tagLine + ", puuid=" + puuid + ", profileIconId="
				+ profileIconId + ", summonerLevel=" + summonerLevel + ", id=" + id + "]";
	}
	
}
