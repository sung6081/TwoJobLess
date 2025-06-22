package opgg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import opgg.entity.GoogleUser;


public interface GoogleRepository extends JpaRepository<GoogleUser, String> {
	
	@Transactional
	Optional<GoogleUser> findByEmail(String email);
	
}
