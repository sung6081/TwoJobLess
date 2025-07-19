package opgg.dto;

public class SkinDTO {
	
	//Field
	private long id;
	private int num;
	private String name;
	
	//Constructor
	public SkinDTO() {
		
	}
	
	public SkinDTO(long id, int num, String name) {
		super();
		this.id = id;
		this.num = num;
		this.name = name;
	}

	//Getter & Setter
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SkinDTO [id=" + id + ", num=" + num + ", name=" + name + "]";
	}
	
}
