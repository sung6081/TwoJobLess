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
	private String costBurn; //소모값
	private String cooldownBurn; //쿨타임
	private String rangeBurn; //범위
	private String image;
	
	//Constructor
	public Skill() {
		
	}

	public Skill(String name, String description, String tooltip, String costType, String costBurn, String cooldownBurn,
			String rangeBurn, String image) {
		super();
		this.name = name;
		this.description = description;
		this.tooltip = tooltip;
		this.costType = costType;
		this.costBurn = costBurn;
		this.cooldownBurn = cooldownBurn;
		this.rangeBurn = rangeBurn;
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

	public String getCostBurn() {
		return costBurn;
	}

	public void setCostBurn(String costBurn) {
		this.costBurn = costBurn;
	}

	public String getRangeBurn() {
		return rangeBurn;
	}

	public void setRangeBurn(String rangeBurn) {
		this.rangeBurn = rangeBurn;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getCooldownBurn() {
		return cooldownBurn;
	}

	public void setCooldownBurn(String cooldownBurn) {
		this.cooldownBurn = cooldownBurn;
	}

	@Override
	public String toString() {
		return "Skill [name=" + name + ", description=" + description + ", tooltip=" + tooltip + ", costType="
				+ costType + ", costBurn=" + costBurn + ", cooldownBurn=" + cooldownBurn + ", range=" + rangeBurn
				+ ", image=" + image + "]";
	}
	
}
