package opgg.RiotApi;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import opgg.champion.ChampionMastery;
import opgg.champion.RiotChampion;
import opgg.champion.Skill;
import opgg.dto.RiotAccountDTO;

@Service("riotService")
public class RiotServiceImpl implements RiotService {

	//Field
	@Value("${riot.apiKey}")
	private String apiKey;
	
	@Value("${riot.version}")
	private String version;
	
	@Value("${riot.info.url}")
	private String infoUrl;
	
	@Value("${riot.languge}")
	private String language;
	
	@Override
	public Map<String, String> getNameANdKeyMapping() {
		
		System.out.println("start getNameANdKeyMapping api");
		
		String championsUrl = infoUrl + "/" + version + "/data/" + language + "/champion.json";
		System.out.println("getChampionName url : " + championsUrl);
		
		String championsResponse = get(championsUrl);
		
		//모든 챔피언의 이름과 key매핑
		Map<String, String> mappingIdWithKey = new HashMap<>();
		
		try {
			
			JSONObject championsInfo = new JSONObject(championsResponse.toString());
			
			//모든 챔피언 정보
			JSONObject champions = championsInfo.getJSONObject("data");
			
			//챔피언 이름들
			JSONArray names = champions.names();
			
			for(int i = 0; i < names.length(); i++) {
				
				JSONObject champion = champions.getJSONObject(names.getString(i));
				
				mappingIdWithKey.put(champion.get("key").toString(), names.get(i).toString());
				
				//System.out.println("key : " + champion.get("key").toString() + " = " + names.get(i).toString());
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("end getNameANdKeyMapping api");
		
		return mappingIdWithKey;
		
	}
	
	@Override
	public List<RiotChampion> getRotationChamps(Map<String, String> mappingIdWithKey) {
		// TODO Auto-generated method stub
		List<RiotChampion> list = new ArrayList<RiotChampion>();
		
		System.out.println("start getRotationChamps api");
		
		String url = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations?api_key=" + apiKey;
		
		String rotationResponse = get(url);
		
		try {
			
			JSONObject rotationInfo = new JSONObject(rotationResponse.toString());
			
			//로테이션 챔피언
			JSONArray rotation = rotationInfo.getJSONArray("freeChampionIds");
			
			for(int i = 0; i < rotation.length(); i++) {
				
				String name = mappingIdWithKey.get(rotation.get(i).toString());
				RiotChampion champ = new RiotChampion();
				
				String championUrl = infoUrl + "/" + version + "/data/" + language + "/champion/" + name + ".json";
				System.out.println("getChampionName url : " + championUrl);
				
				//챔피언 정보
				JSONObject champion = new JSONObject(get(championUrl).toString()).getJSONObject("data").getJSONObject(name);
				//System.out.println(champion);
				//챔피언 passive 정보
				JSONObject passive = champion.getJSONObject("passive");
				//챔피언 스킬들 정보
				JSONArray skills = champion.getJSONArray("spells");
				
				champ.setId(champion.getString("id"));
				champ.setKey(Integer.parseInt(rotation.get(i).toString()));
				champ.setTitle(champion.getString("title"));
				champ.setImage(champion.getJSONObject("image").getString("full"));
				champ.setLore(champion.getString("lore"));
				champ.setPassive(passive.getString("name"));
				champ.setPassiveDescription(passive.getString("description"));
				champ.setPassiveImage(passive.getJSONObject("image").getString("full"));
				
				list.add(champ);
				
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//System.out.println(list);
		
		System.out.println("end getRotationChamps api");
		
		return list;
	}
	
	@Override
	public RiotAccountDTO getRiotAccountWithGameName(String gameName, String tagLine) {
		// TODO Auto-generated method stub
		RiotAccountDTO riotAccountDTO = new RiotAccountDTO();
		
		System.out.println("start getRiotAccountWithGameName api");
		System.out.println(apiKey);
		
		try {
			gameName = URLEncoder.encode(gameName, StandardCharsets.UTF_8);
			tagLine = URLEncoder.encode(tagLine, StandardCharsets.UTF_8);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		String url = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" +gameName.replaceAll("\\+", "%20")+ "/" +tagLine.replaceAll("\\+", "%20")+ "?api_key=" +apiKey;
		
		System.out.println("url : "+url);
		
		String responseBody = get(url);
		//System.out.println(responseBody);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
            riotAccountDTO = objectMapper.readValue(responseBody, RiotAccountDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		System.out.println("end getRiotAccountWithGameName api");
		
		return riotAccountDTO;
	}
	
	@Override
	public List<ChampionMastery> getMasteryWithGameName(String puuid, Map<String, String> mappingIdWithKey) {
		
		System.out.println("start getMasteryWithGameName api");
		
		String url = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/" + puuid + "?api_key=" + apiKey;
		
		System.out.println("url : " + url);
		
		String responseBody = get(url);
		
		JSONArray masteries = new JSONArray(responseBody.toString());
		//System.out.println(masteries);
		
		List<ChampionMastery> championMatsteried = new ArrayList<>();
		
		for(int i = 0; i < masteries.length(); i++) {
			
			JSONObject mastery = masteries.getJSONObject(i);
			
			ChampionMastery championMastery = new ChampionMastery();
			championMastery.setChampionId(mastery.getInt("championId"));
			championMastery.setChampionName(mappingIdWithKey.get(new Integer(mastery.getInt("championId")).toString()));
			championMastery.setChampionLevel(mastery.getInt("championLevel"));
			championMastery.setChampionPoints(mastery.getInt("championPoints"));
			championMastery.setLastPlayTime(mastery.getInt("lastPlayTime"));
			championMastery.setChampionPointsSinceLastLevel(mastery.getInt("championPointsSinceLastLevel"));
			championMastery.setChampionPointsUntilNextLevel(mastery.getInt("championPointsUntilNextLevel"));
			championMastery.setMarkRequiredForNextLevel(mastery.getInt("markRequiredForNextLevel"));
			championMastery.setTokensEarned(mastery.getInt("tokensEarned"));
			championMastery.setChampionSeasonMilestone(mastery.getInt("championSeasonMilestone"));
			
			championMatsteried.add(championMastery);
			
		}
		
		System.out.println("end getMasteryWithGameName api");
		
		return championMatsteried;
		
	}
	
	private static String get(String apiUrl){
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 오류 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl){
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection)url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }


    private static String readBody(InputStream body){
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
            
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는 데 실패했습니다.", e);
        }
    }
	
}
