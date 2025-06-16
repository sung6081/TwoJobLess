package opgg.user;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import opgg.dto.GoogleDTO;

@Service("googleService")
public class GoogleServiceImpl implements GoogleService {
	
	//Field
	@Value("${google.login.client.id}")
	private String clientId;
	
	@Value("${google.login.client.pw}")
	private String clientPw;
	
	@Value("${google.login.redirect}")
	private String redirectUri;
	
	@Override
	public String getAccessToken(String code) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		String tokenUrl = "https://oauth2.googleapis.com/token";
        
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("code", code);
        params.add("client_id", clientId);
        params.add("client_secret", clientPw);
        params.add("redirect_uri", redirectUri);
        params.add("grant_type", "authorization_code");
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        HttpEntity<?> request = new HttpEntity<>(params, headers);

        ResponseEntity<Map> tokenResponse = restTemplate.exchange(
                tokenUrl, HttpMethod.POST, request, Map.class);
		
        return (String) tokenResponse.getBody().get("access_token");
	}

	@Override
	public GoogleDTO getUserInfo(String accessToken) {
		// TODO Auto-generated method stub
		RestTemplate restTemplate = new RestTemplate();
		
		HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        HttpEntity<?> request = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
            "https://www.googleapis.com/oauth2/v2/userinfo",
            HttpMethod.GET,
            request,
            Map.class
        );

        Map<String, Object> userInfo = response.getBody();

        if (userInfo == null) return null;

        return GoogleDTO.builder()
            .id((String) userInfo.get("id"))               // Google에서 'sub' 대신 'id'로 제공
            .name((String) userInfo.get("name"))
            .email((String) userInfo.get("email"))
            .picture((String) userInfo.get("picture"))
            .build();
    }

}
