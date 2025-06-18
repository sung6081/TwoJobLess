package opgg.champion;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

public class Skill {

	//Field
	private String name;
	private String description; //스킬 설명
	private String tooltip;
	private String costType;
	private List<Integer> cost; //소모값
	private List<Integer> range;
	private String image;
	
	//Constructor
	public Skill() {
		
	}

	public Skill(String name, String description, String tooltip, String costType, List<Integer> cost,
			List<Integer> range, String image) {
		super();
		this.name = name;
		this.description = description;
		this.tooltip = tooltip;
		this.costType = costType;
		this.cost = cost;
		this.range = range;
		this.image = image;
	}

	//Getter & Setter
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getTooltip() {
		return tooltip;
	}

	public void setTooltip(String tooltip) {
		this.tooltip = tooltip;
	}

	public String getCostType() {
		return costType;
	}

	public void setCostType(String costType) {
		this.costType = costType;
	}

	public List<Integer> getCost() {
		return cost;
	}

	public void setCost(List<Integer> cost) {
		this.cost = cost;
	}

	public List<Integer> getRange() {
		return range;
	}

	public void setRange(List<Integer> range) {
		this.range = range;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Skill [name=" + name + ", description=" + description + ", tooltip=" + tooltip + ", costType="
				+ costType + ", cost=" + cost + ", range=" + range + ", image=" + image + "]";
	}
	
}
