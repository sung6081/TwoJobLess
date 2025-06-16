package opgg.champion;

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
	
	//Constructor
	public RiotChampion() {
		
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

	public Skill getSkillQ() {
		return skillQ;
	}

	public void setSkillQ(Skill skillQ) {
		this.skillQ = skillQ;
	}

	public Skill getSkillW() {
		return skillW;
	}

	public void setSkillW(Skill skillW) {
		this.skillW = skillW;
	}

	public Skill getSkillE() {
		return skillE;
	}

	public void setSkillE(Skill skillE) {
		this.skillE = skillE;
	}

	public Skill getSkillR() {
		return skillR;
	}

	public void setSkillR(Skill skillR) {
		this.skillR = skillR;
	}

	@Override
	public String toString() {
		return "RiotChampion [id=" + id + ", key=" + key + ", title=" + title + ", image=" + image + ", lore=" + lore
				+ ", passive=" + passive + ", passiveDescription=" + passiveDescription + ", passiveImage="
				+ passiveImage + ", skillQ=" + skillQ + ", skillW=" + skillW + ", skillE=" + skillE + ", skillR="
				+ skillR + "]";
	}
	
}
