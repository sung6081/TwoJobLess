package opgg.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Limit;
import org.springframework.data.jpa.repository.JpaRepository;

import jakarta.transaction.Transactional;
import opgg.entity.RiotAccount;

public interface RiotAccountRepository extends JpaRepository<RiotAccount, String> {
	
	@Transactional
	Optional<RiotAccount> findByPuuid(String puuid);
	
	@Transactional
	List<RiotAccount> findByGameNameLikeOrderByGameNameDesc(String gameName, Limit limit);
	
}
