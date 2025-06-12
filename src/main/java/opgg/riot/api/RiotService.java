package opgg.riot.api;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import opgg.champion.ChampionMastery;
import opgg.champion.RiotChampion;
import opgg.dto.RiotAccountDTO;

public interface RiotService {
	
	public Map<String, String> getNameAndKeyMapping();
	
	public RiotChampion getChampion(int key, Map<String, String> mappingIdWithKey);
	
	public List<RiotChampion> getRotationChamps(Map<String, String> mappingIdWithKey);
	
	public RiotAccountDTO getRiotAccountWithGameName(String gameName, String tagLine);
	
	public List<ChampionMastery> getMasteryWithGameName(String puuid, Map<String, String> mappingIdWithKey);
	
}
