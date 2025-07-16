package opgg.champion;

import java.util.List;

public class RiotChampion {

	//Field
	private String id; //챔피언 이름
	private int key; //구분 번호
	private String name; //챔피언 이름
	private String title;
	private String image; //작은 사각 image
	private String lore; //챔피언 설명
	private String passive; //passive name
	private String passiveDescription; //pasive description
	private String passiveImage; //passive image
	private List<Skill> skills;
	private boolean isRotation; //로테이션 여부
	
	//Constructor
	public RiotChampion() {
		
	}

	public RiotChampion(String id, int key, String name, String title, String image, String lore, String passive,
			String passiveDescription, String passiveImage, List<Skill> skills) {
		super();
		this.id = id;
		this.key = key;
		this.name = name;
		this.title = title;
		this.image = image;
		this.lore = lore;
		this.passive = passive;
		this.passiveDescription = passiveDescription;
		this.passiveImage = passiveImage;
		this.skills = skills;
		this.isRotation = false;
	}

	//Getter & Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLore() {
		return lore;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}

	public String getPassive() {
		return passive;
	}

	public void setPassive(String passive) {
		this.passive = passive;
	}

	public String getPassiveDescription() {
		return passiveDescription;
	}

	public void setPassiveDescription(String passiveDescription) {
		this.passiveDescription = passiveDescription;
	}

	public String getPassiveImage() {
		return passiveImage;
	}

	public void setPassiveImage(String passiveImage) {
		this.passiveImage = passiveImage;
	}

	public List<Skill> getSkills() {
		return skills;
	}

	public void setSkills(List<Skill> skills) {
		this.skills = skills;
	}

	public boolean isRotation() {
		return isRotation;
	}

	public void setRotation(boolean isRotation) {
		this.isRotation = isRotation;
	}

	@Override
	public String toString() {
		return "RiotChampion [id=" + id + ", key=" + key + ", name=" + name + ", title=" + title + ", image=" + image
				+ ", lore=" + lore + ", passive=" + passive + ", passiveDescription=" + passiveDescription
				+ ", passiveImage=" + passiveImage + ", skills=" + skills + ", isRotation=" + isRotation + "]";
	}
	
}
