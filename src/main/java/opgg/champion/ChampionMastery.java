package opgg.champion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ChampionMastery {

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
	
}
