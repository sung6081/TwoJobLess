package opgg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import opgg.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByEmail(String email); // 중복 이메일 체크 용도
    Optional<User> findByEmail(String email);
    Optional<User> findByVerifyToken(String token);
}