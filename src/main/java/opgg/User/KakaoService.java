package opgg.User;

import opgg.dto.KakaoDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class KakaoService {

    public KakaoDTO getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken); // Authorization: Bearer {accessToken}

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                request,
                Map.class
        );

        Map<String, Object> userData = response.getBody();

        // DTO로 변환해서 반환
        return new KakaoDTO(userData);
    }
}
