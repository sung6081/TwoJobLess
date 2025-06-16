package opgg.champion;

public class ChampionMastery {

	//Feild
	RiotChampion champion;
	private int championLevel;
	private int lastPlayTime;
	private int championPoints;
	private int championPointsSinceLastLevel;
	private int championPointsUntilNextLevel;
	private int markRequiredForNextLevel;
	private int tokensEarned;
	private int championSeasonMilestone;
	//private String milestoneGrades;
	//private String nextSeasonMilestone;
	
	//Constructor
	public ChampionMastery() {
		
	}
	
	//Getter & Setter
	public RiotChampion getChampion() {
		return champion;
	}
	public void setChampion(RiotChampion champion) {
		this.champion = champion;
	}
	public int getChampionLevel() {
		return championLevel;
	}
	public void setChampionLevel(int championLevel) {
		this.championLevel = championLevel;
	}
	public int getLastPlayTime() {
		return lastPlayTime;
	}
	public void setLastPlayTime(int lastPlayTime) {
		this.lastPlayTime = lastPlayTime;
	}
	public int getChampionPoints() {
		return championPoints;
	}
	public void setChampionPoints(int championPoints) {
		this.championPoints = championPoints;
	}
	public int getChampionPointsSinceLastLevel() {
		return championPointsSinceLastLevel;
	}
	public void setChampionPointsSinceLastLevel(int championPointsSinceLastLevel) {
		this.championPointsSinceLastLevel = championPointsSinceLastLevel;
	}
	public int getChampionPointsUntilNextLevel() {
		return championPointsUntilNextLevel;
	}
	public void setChampionPointsUntilNextLevel(int championPointsUntilNextLevel) {
		this.championPointsUntilNextLevel = championPointsUntilNextLevel;
	}
	public int getMarkRequiredForNextLevel() {
		return markRequiredForNextLevel;
	}
	public void setMarkRequiredForNextLevel(int markRequiredForNextLevel) {
		this.markRequiredForNextLevel = markRequiredForNextLevel;
	}
	public int getTokensEarned() {
		return tokensEarned;
	}
	public void setTokensEarned(int tokensEarned) {
		this.tokensEarned = tokensEarned;
	}
	public int getChampionSeasonMilestone() {
		return championSeasonMilestone;
	}
	public void setChampionSeasonMilestone(int championSeasonMilestone) {
		this.championSeasonMilestone = championSeasonMilestone;
	}

	@Override
	public String toString() {
		return "ChampionMastery [champion=" + champion + ", championLevel=" + championLevel + ", lastPlayTime="
				+ lastPlayTime + ", championPoints=" + championPoints + ", championPointsSinceLastLevel="
				+ championPointsSinceLastLevel + ", championPointsUntilNextLevel=" + championPointsUntilNextLevel
				+ ", markRequiredForNextLevel=" + markRequiredForNextLevel + ", tokensEarned=" + tokensEarned
				+ ", championSeasonMilestone=" + championSeasonMilestone + "]";
	}
	
}
