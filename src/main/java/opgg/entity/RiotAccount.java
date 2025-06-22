package opgg.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "RiotAccount")
public class RiotAccount {

	@Id
	@Column(name = "puuid", length = 100, nullable = false)
	private String puuid;
	
	@Column(name = "game_name", length = 50, nullable = false)
	private String gameName;
	
	@Column(name = "tag_line", length = 50, nullable = false)
	private String tagLine;
	
	//Constructor
	public RiotAccount() {
		
	}
	
	public RiotAccount(String puuid, String gameName, String tagLine) {
		super();
		this.puuid = puuid;
		this.gameName = gameName;
		this.tagLine = tagLine;
	}

	public static RiotAccount of(String puuid, String gameName, String tagLine) {
		return new RiotAccount(puuid, gameName, tagLine);
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
	
}
