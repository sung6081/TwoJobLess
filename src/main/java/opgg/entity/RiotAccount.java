package opgg.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "RiotAccount")
public class RiotAccount {

	@Id
	@GeneratedValue
	@Column(name = "puuid", length = 100, nullable = false)
	private String puuid;
	
	@Column(name = "game_name", length = 50, nullable = false)
	private String gameName;
	
	@Column(name = "tag_line", length = 50, nullable = false)
	private String tagLine;
	
}
