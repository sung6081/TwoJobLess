package opgg.api.riot;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import opgg.champion.ChampionMastery;
import opgg.champion.RiotChampion;
import opgg.dto.MatchDetailDTO;
import opgg.dto.RankDTO;
import opgg.dto.RiotAccountDTO;
import opgg.dto.SpectatorMatchDTO;

public interface RiotService {
	
	public Map<String, String> getChampAndKeyMapping(); //챔피언 id, key 매핑
	
	public Map<String, String> getSpellAndKeyMapping(); //스펠 id, key 매핑
	
	public RiotChampion getChampion(int key, Map<String, String> mappingIdWithKey, boolean isFull); //챔피언 정보
	
	public List<RiotChampion> getRotationChamps(Map<String, String> mappingIdWithKey); //로테이션 챔피언 정보
	
	public List<RiotChampion> getAllChamps(Map<String, String> mappingIdWithKey); //모든 챔피언 정보
	
	public RiotAccountDTO getRiotAccountWithGameName(String gameName, String tagLine); //닉네임으로 계정 정보
	
	public RiotAccountDTO getRiotAccountWithPuuid(String puuid); //Puuid로 계정 정보
	
	public RiotAccountDTO getSummonerByPuuid(RiotAccountDTO riotAccountDTO); //소환사 정보
	
	public List<ChampionMastery> getMasteryWithGameName(String puuid, Map<String, String> mappingIdWithKey); //숙련도 정보

	public List<MatchDetailDTO> getRecentMatchDetail(String gameName, String tagLine);

	public MatchDetailDTO getMatchDetail(String matchId, String puuid);

	public List<RankDTO> getRankByPuuid(String puuid, boolean onlyRank); //랭크 정보
	
	public Map<String, Map<String, List<MatchDetailDTO>>> getRecentMatchDetailCategorized(String gameName, String tagLine);
	
	public SpectatorMatchDTO getSpectatorMatch(String puuid, Map<String, String> mappingIdWithKey); //인게임 정보
	
}
