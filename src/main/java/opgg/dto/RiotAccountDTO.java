package opgg.dto;

public class RiotAccountDTO {
	
	//Field
	private String gameName;
	private String tagLine;
	private String puuid;
	
	//Constuctor
	public RiotAccountDTO() {
		
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
	
}
