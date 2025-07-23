package opgg.dto;

public class SpellDTO {

	//Field
	private int key; //식별번호
	private String name; //이름
	private String description; //설명
	private String tooltip; //효과
	private String cooldownBurn; //쿨타임
	private String rangeBurn; //범위
	private String image; //full
	
	//Constructor
	public SpellDTO() {
		
	}

	public SpellDTO(int key, String name, String description, String tooltip, String cooldownBurn, String rangeBurn,
			String image) {
		super();
		this.key = key;
		this.name = name;
		this.description = description;
		this.tooltip = tooltip;
		this.cooldownBurn = cooldownBurn;
		this.rangeBurn = rangeBurn;
		this.image = image;
	}

	//Getter & Setter
	public int getKey() {
		return key;
	}

	public void setKey(int key) {
		this.key = key;
	}

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

	public String getCooldownBurn() {
		return cooldownBurn;
	}

	public void setCooldownBurn(String cooldownBurn) {
		this.cooldownBurn = cooldownBurn;
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

	@Override
	public String toString() {
		return "SpellDTO [key=" + key + ", name=" + name + ", description=" + description + ", tooltip=" + tooltip
				+ ", cooldownBurn=" + cooldownBurn + ", rangeBurn=" + rangeBurn + ", image=" + image + "]";
	}
	
}
