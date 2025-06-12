package opgg.user;

import opgg.dto.KakaoDTO;

public interface KakaoService {
	
    KakaoDTO getUserInfo(String accessToken);
}
