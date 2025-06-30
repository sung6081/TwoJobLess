package opgg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RiotAccount")
public class RiotAccount {

	@Id
	@Column(name = "puuid", length = 100, nullable = false)
	private String puuid;
	
	@Column(name = "game_name", length = 100, nullable = false)
	private String gameName;
	
	@Column(name = "tag_line", length = 100, nullable = false)
	private String tagLine;
	
	@Column(name = "profile_icon_id")
	private long profileIconId;
	
	@Column(name = "summoner_level")
	private long summonerLevel;
	
	//Constructor
	public RiotAccount() {
		
	}
	
	public RiotAccount(String puuid, String gameName, String tagLine) {
		this.puuid = puuid;
		this.gameName = gameName;
		this.tagLine = tagLine;
	}

	public static RiotAccount of(String puuid, String gameName, String tagLine) {
		return new RiotAccount(puuid, gameName, tagLine);
	}
	
	public static RiotAccount of(String puuid, String gameName, String tagLine, long profileIconId, long summonerLevel) {
		return new RiotAccount(puuid, gameName, tagLine, profileIconId, summonerLevel);
	}
	
	public RiotAccount(String puuid, String gameName, String tagLine, long profileIconId, long summonerLevel) {
		this.puuid = puuid;
		this.gameName = gameName;
		this.tagLine = tagLine;
		this.profileIconId = profileIconId;
		this.summonerLevel = summonerLevel;
	}

	//Getter & Setter
	public String getPuuid() {
		return puuid;
	}

	public void setPuuid(String puuid) {
		this.puuid = puuid;
	}

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
