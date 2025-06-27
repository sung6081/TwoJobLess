package opgg.api.riot;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Limit;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import opgg.champion.ChampionMastery;
import opgg.champion.RiotChampion;
import opgg.dto.MatchDetailDTO;
import opgg.dto.RiotAccountDTO;
import opgg.entity.RiotAccount;
import opgg.repository.RiotAccountRepository;

@RestController
@RequestMapping("/opgg/riotapi/*")
@CrossOrigin(origins = "http://localhost:3000")
public class RiotRestController {

   @Autowired
   @Qualifier("riotService")
   private RiotService riotService;
   
   @Autowired
   private RiotAccountRepository riotAccountRepository;
   
   @GetMapping("getPuuidFromGameName/{gameName}/{tagLine}")
   public RiotAccountDTO getRiotAccountWithGameName(
         @PathVariable("gameName") String gameName, 
         @PathVariable("tagLine") String tagLine) throws Exception {
      
      System.out.println("getRiotAccountWithGameName");
      
      RiotAccountDTO riotAccountDTO = riotService.getRiotAccountWithGameName(gameName, tagLine);
      riotAccountDTO = riotService.getSummonerByPuuid(riotAccountDTO);
      
      Optional<RiotAccount> optional = riotAccountRepository.findByPuuid(riotAccountDTO.getPuuid());
      
      //처음 검색되었다면 insert
      if(optional.isEmpty()) {
    	  RiotAccount newAccount = new RiotAccount().of(riotAccountDTO.getPuuid(), riotAccountDTO.getGameName(), riotAccountDTO.getTagLine());
    	  riotAccountRepository.save(newAccount);
      }else {
    	  RiotAccount riotAccount = optional.get();
    	  if(!riotAccount.getGameName().equals(riotAccountDTO.getGameName()) || !riotAccount.getTagLine().equals(riotAccountDTO.getTagLine())) {
        	  
        	  RiotAccount updateAccount = new RiotAccount().of(riotAccountDTO.getPuuid(), riotAccountDTO.getGameName(), riotAccountDTO.getTagLine());
        	  riotAccountRepository.save(updateAccount);
        	  
          }
      }
      
      return riotAccountDTO;
      
   }
   
   @GetMapping("getRiotAccountsWithGameNameLike/{gameNamePart}")
   public List<RiotAccountDTO> getRiotAccountsWithGameNameLike(@PathVariable String gameNamePart) throws Exception {
	   
	   System.out.println("getRiotAccountsWithGameNameLike");
	   
	   List<RiotAccountDTO> riotAccounts = new ArrayList<RiotAccountDTO>();
	   
	   List<RiotAccount> list = riotAccountRepository.findByGameNameLikeOrderByGameNameDesc(gameNamePart, Limit.of(3));
	   
	   for(int i = 0; i < list.size(); i++) {
		   riotAccounts.add(new RiotAccountDTO(list.get(i)));
	   }
	   
	   return riotAccounts;
	   
   }
   
   @GetMapping("getMasteriesWithGameName/{gameName}/{tagLine}")
   public List<ChampionMastery> getMasteriesWithGameName(
         @PathVariable("gameName") String gameName,
         @PathVariable("tagLine") String tagLine) throws Exception {
      
      System.out.println("getMasteriesWithGameName");
      
      String puuid = riotService.getRiotAccountWithGameName(gameName, tagLine).getPuuid();
      System.out.println("puuid : " + puuid);
      
      Map<String, String> mappingIdWithKey = riotService.getNameAndKeyMapping();
      
      return riotService.getMasteryWithGameName(puuid, mappingIdWithKey);
      
   }
   
   @GetMapping("getRotationChamps")
   public List<RiotChampion> getRotationChamps() throws Exception {
      
      System.out.println("getRotationChamps");
      
      return riotService.getRotationChamps(riotService.getNameAndKeyMapping());
      
   }
   
   @GetMapping("getRecentMatches/{gameName}/{tagLine}")
   public List<MatchDetailDTO> getRecentMatches(
           @PathVariable("gameName") String gameName,
           @PathVariable("tagLine") String tagLine) throws Exception {
       
       System.out.println("getRecentMatches");

       return riotService.getRecentMatchDetail(gameName, tagLine);
   }
   
}
