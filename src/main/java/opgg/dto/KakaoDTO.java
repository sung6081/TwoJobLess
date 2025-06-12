package opgg.dto;

import java.util.Map;

public class KakaoDTO {
    private final String nickname;
    private final String profileImage;

    public KakaoDTO(Map<String, Object> attributes) {
        this.nickname = (String) attributes.get("profile_nickname");
        this.profileImage = (String) attributes.get("profile_image");
    }

    public String getNickname() { return nickname; }
    public String getProfileImage() { return profileImage; }
}
