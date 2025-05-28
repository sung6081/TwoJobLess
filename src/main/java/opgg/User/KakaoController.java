package opgg.User;

import opgg.dto.KakaoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:3000") // Vue 개발 서버 주소
public class KakaoController {

    @Autowired
    private KakaoService kakaoService;

    // 프론트에서 accessToken을 JSON으로 전달
    @PostMapping("/kakao")
    public KakaoDTO kakaoLogin(@RequestBody Map<String, String> body) {
        String accessToken = body.get("accessToken");
        return kakaoService.getUserInfo(accessToken);
    }
}
