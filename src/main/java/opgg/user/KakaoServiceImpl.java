package opgg.user;

import opgg.dto.KakaoDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@Service
public class KakaoServiceImpl implements KakaoService {

    @Value("${kakao.api.user-info-url}")
    private String kakaoUserInfoUrl;

    @Override
    public KakaoDTO getUserInfo(String accessToken) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(accessToken);

        HttpEntity<Void> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                kakaoUserInfoUrl,
                HttpMethod.GET,
                request,
                Map.class
        );

        return new KakaoDTO(response.getBody());
    }
}
