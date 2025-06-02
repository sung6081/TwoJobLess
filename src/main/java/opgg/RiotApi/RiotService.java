package opgg.RiotApi;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpSession;
import opgg.champion.RiotChampion;
import opgg.dto.RiotAccountDTO;

public interface RiotService {
	
	public Map<String, String> getNameANdKeyMapping();
	
	public List<RiotChampion> getRotationChamps(Map<String, String> mappingIdWithKey);
	
	public RiotAccountDTO getRiotAccountWithGameName(String gameName, String tagLine);
	
}
