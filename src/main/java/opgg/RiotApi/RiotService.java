package opgg.RiotApi;

import org.springframework.stereotype.Service;

import opgg.dto.RiotAccountDTO;

public interface RiotService {
	
	public RiotAccountDTO getRiotAccountWithGameName(String gameName, String tagLine);
	
}
