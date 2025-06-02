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
public class Skill {

	//Field
	private String name;
	private String description; //스킬 설명
	private String tooltip;
	private String costType; //소모값
	private String range;
	private String image;
	
}
