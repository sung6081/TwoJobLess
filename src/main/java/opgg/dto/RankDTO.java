package opgg.dto;

public class RankDTO {
	
		//Field
	    private String queueType;
	    private String tier;
	    private String rank;
	    private int leaguePoints;
	    private int wins;
	    private int losses;
	    
	    //Getter & Setter
		public String getQueueType() {
			return queueType;
		}
		public void setQueueType(String queueType) {
			this.queueType = queueType;
		}
		public String getTier() {
			return tier;
		}
		public void setTier(String tier) {
			this.tier = tier;
		}
		public String getRank() {
			return rank;
		}
		public void setRank(String rank) {
			this.rank = rank;
		}
		public int getLeaguePoints() {
			return leaguePoints;
		}
		public void setLeaguePoints(int leaguePoints) {
			this.leaguePoints = leaguePoints;
		}
		public int getWins() {
			return wins;
		}
		public void setWins(int wins) {
			this.wins = wins;
		}
		public int getLosses() {
			return losses;
		}
		public void setLosses(int losses) {
			this.losses = losses;
		}
		
		@Override
		public String toString() {
			return "RankDTO [queueType=" + queueType + ", tier=" + tier + ", rank=" + rank + ", leaguePoints="
					+ leaguePoints + ", wins=" + wins + ", losses=" + losses + "]";
		}
	    
}
