package opgg.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import opgg.entity.GoogleUser;


public interface GoogleRepository extends JpaRepository<GoogleUser, Long> {
	Optional<GoogleUser> findByEmail(String email);
}
