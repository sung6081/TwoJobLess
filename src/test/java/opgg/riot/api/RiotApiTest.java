package opgg.riot.api;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

import opgg.api.riot.RiotService;
import opgg.api.riot.RiotServiceImpl;
import opgg.champion.ChampionMastery;
import opgg.champion.RiotChampion;
import opgg.dto.RiotAccountDTO;

@SpringBootTest
public class RiotApiTest {

	@Autowired
	@Qualifier("riotService")
	private RiotService riotService;
	//private RiotService riotService = new RiotServiceImpl();
	
	//@Test
	public void testGetRiotAccountWithGameName() {
		
		RiotAccountDTO riotAccountDTO1 =  riotService.getRiotAccountWithGameName("방학동 짜글이", "1132");
		RiotAccountDTO riotAccountDTO2 =  riotService.getRiotAccountWithGameName("그마갈 사나이", "곧 감");
		RiotAccountDTO riotAccountDTO3 =  riotService.getRiotAccountWithGameName("hide on bush", "KR1");
		
		System.out.println("test1 : "+riotAccountDTO1);
		System.out.println("test2 : "+riotAccountDTO2);
		System.out.println("test3 : "+riotAccountDTO3);
		
	}
	
	//@Test
	public void testGetChampionName() {
		
		Map<String, String> map = riotService.getNameAndKeyMapping();
		
		riotService.getRotationChamps(map);
		
	}
	
	//@Test
	public void getChampion() {
		
		Map<String, String> map = riotService.getNameAndKeyMapping();
		RiotChampion champion = riotService.getChampion(1, map);
		
		System.out.println(champion);
		
	}
	
	//@Test
	public void getRotation() {
		
		Map<String, String> map = riotService.getNameAndKeyMapping();
		
		List<RiotChampion> list = riotService.getRotationChamps(map);
		
		System.out.println(list);
		
	}
	
	//@Test
	public void testGetChampionMastery() {
		
		Map<String, String> map = riotService.getNameAndKeyMapping();
		
		List<ChampionMastery> list = riotService.getMasteryWithGameName("6R9vfGofyzyUGF6Uj7VgQ7kh6Fv33hTWoVJvPmfg-Q4YSK-x_LLoxU2d9DMhR81WQhBsCJQosRSLEQ", map);
		
		System.out.println(list);
		
	}
	
}
