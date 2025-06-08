package opgg.user;

import opgg.dto.KakaoDTO;
import opgg.entity.KakaoUser;
import opgg.repository.KakaoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/oauth/kakao")
@CrossOrigin(origins = "http://localhost:3000") 
public class KakaoController {

    private final KakaoRepository kakaoRepository;

    public KakaoController(KakaoRepository kakaoRepository) {
        this.kakaoRepository = kakaoRepository;
    }

    @PostMapping("/login")
    public String kakaoLogin(@RequestBody Map<String, Object> body) {
        System.out.println("📥 받은 데이터: " + body);

        KakaoDTO kakaoDTO = new KakaoDTO(body);

        Optional<KakaoUser> optionalUser = kakaoRepository.findByNickname(kakaoDTO.getNickname());

        if (optionalUser.isPresent()) {
            return "카카오 로그인 성공: " + optionalUser.get().getNickname();
        }

        KakaoUser newUser = KakaoUser.of(kakaoDTO.getNickname(), kakaoDTO.getProfileImage());
        kakaoRepository.save(newUser);

        return "카카오 회원가입 및 로그인 성공: " + newUser.getNickname();
    }
}
