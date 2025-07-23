package opgg.dto;

public class RuneDTO {

	//Field
	private long id; //식별번호
	private boolean isSelected; //선택된 룬인가
	private String icon; //이미지 경로
	private String name; //이름
	private String longDesc; //설명
	
	
	//Constructor
	public RuneDTO() {
		
	}

	public RuneDTO(long id, boolean isSelected, String icon, String name, String longDesc) {
		super();
		this.id = id;
		this.isSelected = isSelected;
		this.icon = icon;
		this.name = name;
		this.longDesc = longDesc;
	}

	//Getter & Setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isSelected() {
		return isSelected;
	}

	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLongDesc() {
		return longDesc;
	}

	public void setLongDesc(String longDesc) {
		this.longDesc = longDesc;
	}

	@Override
	public String toString() {
		return "RuneDTO [id=" + id + ", isSelected=" + isSelected + ", icon=" + icon + ", name=" + name + ", longDesc="
				+ longDesc + "]";
	}

}
