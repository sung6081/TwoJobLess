package opgg.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "google_user")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

	public static GoogleUser of(String id, String email, String name, String picture) {
		// TODO Auto-generated method stub
		return new GoogleUser(id, email, name, picture);
	}
	
}
