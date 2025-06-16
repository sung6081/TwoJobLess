package opgg.user;

import opgg.dto.GoogleDTO;

public interface GoogleService {
	
	String getAccessToken(String code);

	GoogleDTO getUserInfo(String accessToken);
	
}
