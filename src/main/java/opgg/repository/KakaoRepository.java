package opgg.repository;

import opgg.entity.KakaoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KakaoRepository extends JpaRepository<KakaoUser, Long> {
    Optional<KakaoUser> findByNickname(String nickname);
}
