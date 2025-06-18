package opgg.api.riot;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import opgg.champion.ChampionMastery;
import opgg.champion.RiotChampion;
import opgg.dto.MatchDetailDTO;
import opgg.dto.RiotAccountDTO;

@RestController
@RequestMapping("/opgg/riotapi/*")
@CrossOrigin(origins = "http://localhost:3000")
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
