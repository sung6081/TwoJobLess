package opgg.dto;

import lombok.Data;

import java.util.Date;
import java.util.Map;

@Data
public class KakaoDTO {
    private Long id;
    private String name;
    private String password;
    private String cellPhone;
    private String email;
    private Date regDate;

    public KakaoDTO(Map<String, Object> attributes) {
        this.id = ((Number) attributes.get("id")).longValue();

        Map<String, Object> kakaoAccount = (Map<String, Object>) attributes.get("kakao_account");
        if (kakaoAccount != null) {
            this.email = (String) kakaoAccount.get("email");
            this.cellPhone = (String) kakaoAccount.get("phone_number");

            Map<String, Object> profile = (Map<String, Object>) kakaoAccount.get("profile");
            if (profile != null) {
                this.name = (String) profile.get("nickname");
            }
        }

        this.password = null; 
        this.regDate = new Date(); 
    }
}
