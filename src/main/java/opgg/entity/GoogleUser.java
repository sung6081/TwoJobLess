package opgg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "google_user")
public class GoogleUser {

	//Field
	@Id
    @Column(length = 100)
	private String id;
	
	@Column(name = "email", length = 100, nullable = false)
	private String email;
	
	@Column(name = "name", length = 50, nullable = false)
	private String name;
	
	@Column(name = "picture", nullable = false)
	private String picture;
	
	//Constructor
	public GoogleUser() {
		
	}

	public GoogleUser(String id, String email, String name, String picture) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.picture = picture;
	}
	
	public static GoogleUser of(String id, String email, String name, String picture) {
		// TODO Auto-generated method stub
		return new GoogleUser(id, email, name, picture);
	}

	//Getter & Setter
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
	
}
