package opgg.RiotApi;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import opgg.champion.RiotChampion;
import opgg.dto.RiotAccountDTO;

@RestController
@RequestMapping("/opgg/riotapi/*")
public class RiotRestController {

	@Autowired
	@Qualifier("riotService")
	private RiotService riotService;
	
	@GetMapping("getPuuidFromGameName/{gameName}/{tagLine}")
	public RiotAccountDTO getRiotAccountWithGameName(
			@PathVariable("gameName") String gameName, 
			@PathVariable("tagLine") String tagLine) throws Exception {
		
		System.out.println("getRiotAccountWithGameName");
		
		return riotService.getRiotAccountWithGameName(gameName, tagLine);
		
	}
	
	@GetMapping("getRotationChamps")
	public List<RiotChampion> getRotationChamps() throws Exception {
		
		return riotService.getRotationChamps(riotService.getNameANdKeyMapping());
		
	}
	
}
