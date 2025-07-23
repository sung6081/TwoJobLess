package opgg.dto;

import java.util.List;

public class PerkDTO {

	//Field
	private long id; //식별번호
	private String icon; //이미지 경로
	private String name; //이름 -> ex)정밀, 지배, 마법, 영감, 결의
	private List<List<RuneDTO>> runes; //룬들 정보
	
	//Constructor
	public PerkDTO() {
		
	}

	public PerkDTO(long id, String icon, String name, List<RuneDTO> mainRune, List<List<RuneDTO>> runes) {
		super();
		this.id = id;
		this.icon = icon;
		this.name = name;
		this.runes = runes;
	}

	//Getter & Setter
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public List<List<RuneDTO>> getRunes() {
		return runes;
	}

	public void setRunes(List<List<RuneDTO>> runes) {
		this.runes = runes;
	}

	@Override
	public String toString() {
		return "PerkDTO [id=" + id + ", icon=" + icon + ", name=" + name + ", runes=" + runes + "]";
	}

}
