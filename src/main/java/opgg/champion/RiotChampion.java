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
public class RiotChampion {

	//Field
	private String id; //챔피언 이름
	private int key; //구분 번호
	private String title;
	private String image; //작은 사각 image
	private String lore; //챔피언 설명
	private String passive; //passive name
	private String passiveDescription; //pasive description
	private String passiveImage; //passive image
	private Skill skillQ;
	private Skill skillW;
	private Skill skillE;
	private Skill skillR;
	
}
