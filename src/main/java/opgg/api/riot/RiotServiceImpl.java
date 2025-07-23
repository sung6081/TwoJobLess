package opgg.api.riot;

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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import opgg.champion.ChampionMastery;
import opgg.champion.RiotChampion;
import opgg.champion.Skill;
import opgg.dto.MatchDetailDTO;
import opgg.dto.ParticipantDetailDTO;
import opgg.dto.ParticipantOfSpectatorDTO;
import opgg.dto.PerkDTO;
import opgg.dto.RankDTO;
import opgg.dto.RiotAccountDTO;
import opgg.dto.RuneDTO;
import opgg.dto.SkinDTO;
import opgg.dto.SpectatorMatchDTO;
import opgg.dto.SpellDTO;

@Service("riotService")
public class RiotServiceImpl implements RiotService {

    @Value("${riot.apiKey}")
    private String apiKey;

    @Value("${riot.version}")
    private String version;

    @Value("${riot.info.url}")
    private String infoUrl;

    @Value("${riot.languge}")
    private String language;

    @Override
    public Map<String, String> getChampAndKeyMapping() {
    	
        //System.out.println("start getNameANdKeyMapping api");
        String championsUrl = infoUrl + "/" + version + "/data/" + language + "/champion.json";
        //System.out.println("getChampionName url : " + championsUrl);

        String championsResponse = get(championsUrl);
        Map<String, String> mappingIdWithKey = new HashMap<>();

        try {
        	
            JSONObject championsInfo = new JSONObject(championsResponse);
            JSONObject champions = championsInfo.getJSONObject("data");
            JSONArray names = champions.names();

            for (int i = 0; i < names.length(); i++) {
                JSONObject champion = champions.getJSONObject(names.getString(i));
                mappingIdWithKey.put(champion.get("key").toString(), names.getString(i));
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("end getNameANdKeyMapping api");
        
        return mappingIdWithKey;
        
    }
    
    @Override
    public Map<String, String> getSpellAndKeyMapping() {
    	
    	//System.out.println("start getSpellAndKeyMapping");
    	
    	String spellUrl = infoUrl + "/" + version + "/data/" + language + "/summoner.json";
    	//System.out.println("getSpellUrl : " + spellUrl);
    	
    	String spellResponse = get(spellUrl);
    	Map<String, String> mappingIdWithKey = new HashMap<>();
    	
    	try {
    		
    		JSONObject spellsInfo = new JSONObject(spellResponse);
    		JSONObject spells = spellsInfo.getJSONObject("data");
    		JSONArray names = spells.names();
    		
    		for(int i = 0; i < names.length(); i++) {
    			JSONObject spell = spells.getJSONObject(names.getString(i));
    			mappingIdWithKey.put(spell.get("key").toString(), names.getString(i));
    		}
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	//System.out.println("end getSpellAndKeyMapping");
    	
    	return mappingIdWithKey;
    	
    }

    @Override
    public RiotChampion getChampion(int key, Map<String, String> mappingIdWithKey, boolean isFull) {
    	
        RiotChampion riotChampion = new RiotChampion();
        String name = mappingIdWithKey.get(Integer.toString(key));
        String championUrl = infoUrl + "/" + version + "/data/" + language + "/champion/" + name + ".json";

        try {
        	
            JSONObject champion = new JSONObject(get(championUrl)).getJSONObject("data").getJSONObject(name);

            riotChampion.setId(champion.getString("id"));
            riotChampion.setKey(key);
            riotChampion.setName(champion.getString("name"));
            riotChampion.setTitle(champion.getString("title"));
            riotChampion.setImage(champion.getJSONObject("image").getString("full"));
            
            //Full info
            if(isFull) {
            	
            	JSONObject passive = champion.getJSONObject("passive");
                JSONArray skills = champion.getJSONArray("spells");
                JSONArray skins = champion.getJSONArray("skins");
            	
            	riotChampion.setSprite(riotChampion.getId()+"_0.jpg");
                riotChampion.setLore(champion.getString("lore"));
                riotChampion.setPassive(passive.getString("name"));
                riotChampion.setPassiveDescription(passive.getString("description"));
                riotChampion.setPassiveImage(passive.getJSONObject("image").getString("full"));

                //skill
                List<Skill> list = new ArrayList<>();
                for (int i = 0; i < skills.length(); i++) {
                	
                    JSONObject jsonObject = skills.getJSONObject(i);
                    Skill skill = new Skill();

                    skill.setName(jsonObject.getString("name"));
                    skill.setDescription(jsonObject.getString("description"));
                    skill.setTooltip(jsonObject.getString("tooltip"));
                    skill.setCostType(jsonObject.getString("costType"));
                    skill.setCostBurn(jsonObject.getString("costBurn"));
                    skill.setCooldownBurn(jsonObject.getString("cooldownBurn"));
                    skill.setRangeBurn(jsonObject.getString("rangeBurn"));

//                    if (!skill.getCostType().equals("소모값 없음")) {
//                        JSONArray array = jsonObject.getJSONArray("cost");
//                        List<Integer> list2 = new ArrayList<>();
//                        for (int j = 0; j < array.length(); j++) list2.add(array.getInt(j));
//                        skill.setCost(list2);
//                    }

//                    JSONArray rangeArray = jsonObject.getJSONArray("range");
//                    List<Integer> rangeList = new ArrayList<>();
//                    for (int j = 0; j < rangeArray.length(); j++) rangeList.add(rangeArray.getInt(j));
//                    skill.setRange(rangeList);

                    skill.setImage(jsonObject.getJSONObject("image").getString("full"));
                    list.add(skill);
                    
                }
                riotChampion.setSkills(list);
                
                //skins
                List<SkinDTO> list2 = new ArrayList<>();
                for(int i = 0; i < skins.length(); i++) {
                	
                	JSONObject jsonObject = skins.getJSONObject(i);
                	SkinDTO skinDTO = new SkinDTO();
                	
                	skinDTO.setId(jsonObject.getInt("id"));
                	skinDTO.setName(jsonObject.getString("name"));
                	skinDTO.setNum(jsonObject.getInt("num"));
                	
                	list2.add(skinDTO);
                	
                }
                riotChampion.setSkins(list2);
            	
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return riotChampion;
    }

    @Override
    public List<RiotChampion> getRotationChamps(Map<String, String> mappingIdWithKey) {
    	
        List<RiotChampion> list = new ArrayList<>();
        //System.out.println("start getRotationChamps api");

        String url = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations?api_key=" + apiKey;
        String rotationResponse = get(url);

        try {
            JSONObject rotationInfo = new JSONObject(rotationResponse);
            JSONArray rotation = rotationInfo.getJSONArray("freeChampionIds");
            for (int i = 0; i < rotation.length(); i++) {
                RiotChampion champ = getChampion(rotation.getInt(i), mappingIdWithKey, false);
                list.add(champ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("end getRotationChamps api");
        return list;
    }
    
    @Override
    public List<RiotChampion> getAllChamps(Map<String, String> mappingIdWithKey) {
    
    	//System.out.println("start getAllChamps");
    	
    	List<RiotChampion> champions = new ArrayList<>();
    	
    	String rotationUrl = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations?api_key=" + apiKey;
    	String rotationResponse = get(rotationUrl);
    	
    	Set<String> rotationBook = new HashSet<>();
    	
    	try {
    		
    		JSONObject rotationInfo = new JSONObject(rotationResponse);
            JSONArray rotation = rotationInfo.getJSONArray("freeChampionIds");
            for(int i = 0; i < rotation.length(); i++) {
            	rotationBook.add(new Integer(rotation.getInt(i)).toString());
            }
            
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	Set<String> ids = mappingIdWithKey.keySet();
    	for(String id : ids) {
    		
    		RiotChampion champ = getChampion(Integer.parseInt(id), mappingIdWithKey, true);
    		if(rotationBook.contains(id)) {
    			champ.setRotation(true);
    		}
    		champions.add(champ);
    		
    	}
    	
    	//System.out.println("end getAllChamps");
    	
    	return champions;
    	
    }

    @Override
    public RiotAccountDTO getRiotAccountWithGameName(String gameName, String tagLine) {
    	
        RiotAccountDTO dto = new RiotAccountDTO();
        //System.out.println("start getRiotAccountWithGameName api");

        try {
            gameName = URLEncoder.encode(gameName, StandardCharsets.UTF_8);
            tagLine = URLEncoder.encode(tagLine, StandardCharsets.UTF_8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String url = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-riot-id/" + gameName.replaceAll("\\+", "%20") + "/" + tagLine.replaceAll("\\+", "%20") + "?api_key=" + apiKey;
        System.out.println("url : " + url);

        String responseBody = get(url);
        if (responseBody.contains("\"status\"")) {
            System.out.println("API 오류 응답: " + responseBody);
            return null;
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            dto = objectMapper.readValue(responseBody, RiotAccountDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("end getRiotAccountWithGameName api");
        return dto;
    }

    @Override
    public RiotAccountDTO getRiotAccountWithPuuid(String puuid) {
    	
        //System.out.println("start getRiotAccountWithPuuid");
        RiotAccountDTO dto = new RiotAccountDTO();
        String url = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-puuid/" + puuid + "?api_key=" + apiKey;
        String responseBody = get(url);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            dto = objectMapper.readValue(responseBody, RiotAccountDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("end getRiotAccountWithPuuid");
        return dto;
    }

    @Override
    public RiotAccountDTO getSummonerByPuuid(RiotAccountDTO riotAccountDTO) {
    	
        //System.out.println("start getSummonerByPuuid");

        String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + riotAccountDTO.getPuuid() + "?api_key=" + apiKey;
        String responseBody = get(url);

        if (responseBody.contains("\"status\"")) {
            //System.out.println("API 오류 응답: " + responseBody);
            return riotAccountDTO;
        }

        try {
            JSONObject summonerInfo = new JSONObject(responseBody);
            //System.out.println(summonerInfo);
            //riotAccountDTO.setId(summonerInfo.getString("id"));
            riotAccountDTO.setProfileIconId(summonerInfo.getLong("profileIconId"));
            riotAccountDTO.setSummonerLevel(summonerInfo.getLong("summonerLevel"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        //System.out.println("end getSummonerByPuuid");
        return riotAccountDTO;
    }
    
    @Override
    public List<ChampionMastery> getMasteryWithGameName(String puuid, Map<String, String> mappingIdWithKey) {
    	
        //System.out.println("start getMasteryWithGameName api");

        String url = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/" + puuid + "?api_key=" + apiKey;
        String responseBody = get(url);
        JSONArray masteries = new JSONArray(responseBody);
        List<ChampionMastery> result = new ArrayList<>();

        for (int i = 0; i < masteries.length(); i++) {
            JSONObject mastery = masteries.getJSONObject(i);
            ChampionMastery cm = new ChampionMastery();
            cm.setChampion(getChampion(mastery.getInt("championId"), mappingIdWithKey, false));
            cm.setChampionLevel(mastery.getInt("championLevel"));
            cm.setChampionPoints(mastery.getInt("championPoints"));
            cm.setLastPlayTime(mastery.getInt("lastPlayTime"));
            cm.setChampionPointsSinceLastLevel(mastery.getInt("championPointsSinceLastLevel"));
            cm.setChampionPointsUntilNextLevel(mastery.getInt("championPointsUntilNextLevel"));
            cm.setMarkRequiredForNextLevel(mastery.getInt("markRequiredForNextLevel"));
            cm.setTokensEarned(mastery.getInt("tokensEarned"));
            cm.setChampionSeasonMilestone(mastery.getInt("championSeasonMilestone"));
            result.add(cm);
        }

        //System.out.println("end getMasteryWithGameName api");
        return result;
    }

    @Override
    public List<MatchDetailDTO> getRecentMatchDetail(String gameName, String tagLine) {
    	
        RiotAccountDTO account = getRiotAccountWithGameName(gameName, tagLine);
        if (account == null || account.getPuuid() == null) return Collections.emptyList();

        String puuid = account.getPuuid();
        List<String> matchIds = getMatchIdsByPuuid(puuid);
        List<MatchDetailDTO> matchDetails = new ArrayList<>();

        for (String matchId : matchIds) {
            MatchDetailDTO detail = getMatchDetail(matchId, puuid);
            if (detail != null) matchDetails.add(detail);
        }

        return matchDetails;
    }

    public List<String> getMatchIdsByPuuid(String puuid) {
    	
        String url = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=50&api_key=" + apiKey;
        List<String> matchIds = new ArrayList<>();

        try {
            String response = get(url);
            ObjectMapper mapper = new ObjectMapper();
            matchIds = mapper.readValue(response, new TypeReference<List<String>>() {});
        } catch (Exception e) {
            e.printStackTrace();
        }

        return matchIds;
    }

    @Override
    public MatchDetailDTO getMatchDetail(String matchId, String puuid) {
    	
        String url = "https://asia.api.riotgames.com/lol/match/v5/matches/" + matchId + "?api_key=" + apiKey;

        try {
            String responseBody = get(url);
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode root = objectMapper.readTree(responseBody);

            JsonNode info = root.path("info");
            JsonNode participantsNode = info.path("participants");
            JsonNode teamsNode = info.path("teams");

            // 팀 승리 여부 맵 생성 (teamId -> win)
            Map<Integer, Boolean> teamWinMap = new HashMap<>();
            for (JsonNode teamNode : teamsNode) {
                int teamId = teamNode.path("teamId").asInt();
                boolean win = "Win".equalsIgnoreCase(teamNode.path("win").asText());
                teamWinMap.put(teamId, win);
            }

            List<ParticipantDetailDTO> participantList = new ArrayList<>();
            MatchDetailDTO matchDetailDTO = null;

            for (JsonNode participant : participantsNode) {
                ParticipantDetailDTO p = new ParticipantDetailDTO();

                p.setSummonerName(participant.path("summonerName").asText());
                p.setRiotGameName(participant.path("riotIdGameName").asText(""));
                p.setRiotTagLine(participant.path("riotIdTagline").asText(""));
                p.setChampionName(participant.path("championName").asText());
                p.setChampLevel(participant.path("champLevel").asInt());

                p.setKills(participant.path("kills").asInt());
                p.setDeaths(participant.path("deaths").asInt());
                p.setAssists(participant.path("assists").asInt());

                p.setItem0(participant.path("item0").asInt());
                p.setItem1(participant.path("item1").asInt());
                p.setItem2(participant.path("item2").asInt());
                p.setItem3(participant.path("item3").asInt());
                p.setItem4(participant.path("item4").asInt());
                p.setItem5(participant.path("item5").asInt());
                p.setItem6(participant.path("item6").asInt());

                p.setSpell1Id(participant.path("summoner1Id").asInt());
                p.setSpell2Id(participant.path("summoner2Id").asInt());

                JsonNode perksStyles = participant.path("perks").path("styles");
                if (perksStyles.isArray() && perksStyles.size() >= 2) {
                    p.setMainRuneId(perksStyles.get(0).path("style").asInt());
                    p.setSubRuneId(perksStyles.get(1).path("style").asInt());
                }

                p.setIndividualPosition(participant.path("individualPosition").asText());

                int teamId = participant.path("teamId").asInt();
                boolean isWin = teamWinMap.getOrDefault(teamId, false);
                p.setTeamId(teamId);
                p.setWin(isWin);

                participantList.add(p);

                if (participant.path("puuid").asText().equals(puuid)) {
                    matchDetailDTO = new MatchDetailDTO();
                    matchDetailDTO.setMatchId(matchId);
                    matchDetailDTO.setGameMode(info.path("gameMode").asText());
                    matchDetailDTO.setQueueId(info.path("queueId").asInt());
                    matchDetailDTO.setGameDuration(info.path("gameDuration").asLong());
                    matchDetailDTO.setGameCreation(info.path("gameCreation").asLong());

                    matchDetailDTO.setChampionName(participant.path("championName").asText());
                    matchDetailDTO.setKills(participant.path("kills").asInt());
                    matchDetailDTO.setDeaths(participant.path("deaths").asInt());
                    matchDetailDTO.setAssists(participant.path("assists").asInt());
                    matchDetailDTO.setWin(isWin);
                }
            }

            if (matchDetailDTO != null) {
                matchDetailDTO.setParticipants(participantList);
            }

            return matchDetailDTO;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    @Override
    public List<RankDTO> getRankByPuuid(String puuid, boolean onlyRank) {
    	
        //System.out.println("start getRankByPuuid");

        List<RankDTO> ranks = new ArrayList<>();
        String url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-puuid/"
                   + puuid + "?api_key=" + apiKey;

        try {
        	
            String responseBody = get(url);

            // Riot API 오류 응답 처리
            if (responseBody.contains("\"status\"")) {
                //System.out.println("API 오류 응답: " + responseBody);
                return ranks;
            }

            JSONArray rankArray = new JSONArray(responseBody);
            for (int i = 0; i < rankArray.length(); i++) {
            	
                JSONObject obj = rankArray.getJSONObject(i);

                RankDTO dto = new RankDTO();
                dto.setQueueType(obj.optString("queueType"));
                if(onlyRank && !dto.getQueueType().equals("RANKED_SOLO_5x5"))	continue;
                dto.setTier(obj.optString("tier"));
                dto.setRank(obj.optString("rank"));
                dto.setLeaguePoints(obj.optInt("leaguePoints"));
                dto.setWins(obj.optInt("wins"));
                dto.setLosses(obj.optInt("losses"));

                ranks.add(dto);
            }
            
        } catch (Exception e) {
            //System.out.println("랭크 정보 가져오기 실패");
            e.printStackTrace();
        }

        //System.out.println("end getRankByPuuid");
        return ranks;
    }
    
    public SpectatorMatchDTO getSpectatorMatch(String puuid, Map<String, String> mappingIdWithKey) {
    	
    	//System.out.println("start getSpectatorMatch");
    	
    	SpectatorMatchDTO spectatorMatchDTO = new SpectatorMatchDTO();
    	
    	String url = "https://kr.api.riotgames.com/lol/spectator/v5/active-games/by-summoner/" + puuid + "?api_key=" + apiKey;
    	
    	try {
    		
    		String responseBody = get(url);
    		
    		// Riot API 오류 응답 처리
            if (responseBody.contains("\"status\"")) {
                System.out.println("API 오류 응답: " + responseBody);
                return spectatorMatchDTO;
            }
    		
    		JSONObject response = new JSONObject(responseBody);
    		
    		//매치 기본 정보
    		spectatorMatchDTO.setGameId(response.getLong("gameId"));
    		spectatorMatchDTO.setMapId(response.getLong("mapId"));
    		spectatorMatchDTO.setGameMode(getGameModeKo(response.getString("gameMode")));
    		spectatorMatchDTO.setGameType(response.getString("gameType"));
    		spectatorMatchDTO.setGameQueueConfigId(response.getLong("gameQueueConfigId"));
    		spectatorMatchDTO.setGameStartTime(response.getLong("gameStartTime"));
    		spectatorMatchDTO.setGameLength(response.getLong("gameLength"));
    		
    		//유저정보
    		JSONArray participants = response.getJSONArray("participants");
    		List<ParticipantOfSpectatorDTO> participantOfSpectatorDTOs = new ArrayList<>();
    		Map<String, String> champsMap = getChampAndKeyMapping(); //침피언 id, key mapping map
    		
    		//참가자들 순회
    		for(int i = 0; i < participants.length(); i++) {
    			
    			//참가자 json
    			JSONObject participant = participants.getJSONObject(i);
    			ParticipantOfSpectatorDTO participantOfSpectatorDTO = new ParticipantOfSpectatorDTO();
    			
    			RiotAccountDTO riotAccountDTO = getRiotAccountWithPuuid(participant.getString("puuid"));
    			riotAccountDTO = getSummonerByPuuid(riotAccountDTO);
    			participantOfSpectatorDTO.setRiotAccountDTO(riotAccountDTO);
    			
    			RankDTO rankDTO = getRankByPuuid(participant.getString("puuid"), true).get(0);
    			participantOfSpectatorDTO.setRankDTO(rankDTO);
    			
    			participantOfSpectatorDTO.setTeamId(participant.getLong("teamId"));
    			
    			//spell 정보
    			List<SpellDTO> spells = new ArrayList<>();
    			SpellDTO spell1 = getSpell(mappingIdWithKey.get(participant.get("spell1Id").toString()));
    			SpellDTO spell2 = getSpell(mappingIdWithKey.get(participant.get("spell2Id").toString()));
    			spells.add(spell1);
    			spells.add(spell2);
    			participantOfSpectatorDTO.setSpells(spells);
    			
    			//champ 정보
    			participantOfSpectatorDTO.setChamp(getChampion(participant.getInt("championId"), champsMap, false));
    			
    			//룬 정보
    			JSONObject perks = participant.getJSONObject("perks");
    			participantOfSpectatorDTO.setPerkStyle(perks.getLong("perkStyle"));
    			participantOfSpectatorDTO.setPerkSubStyle(perks.getLong("perkSubStyle"));
    			
    			//선택된 룬들 저장
    			JSONArray seleted = perks.getJSONArray("perkIds");
    			Set<Long> selectedPerks = new HashSet<>();
    			for(int j = 0; j < seleted.length(); j++) {
    				selectedPerks.add(seleted.getLong(j));
    			}
    			
    			//System.out.println("선택된 룬 json : " + seleted);
    			//System.out.println("선택된 룬 set : " + selectedPerks);
    			
    			participantOfSpectatorDTO.setPerk(getPerk(participantOfSpectatorDTO.getPerkStyle(), true, selectedPerks));
    			participantOfSpectatorDTO.setSubPerk(getPerk(participantOfSpectatorDTO.getPerkSubStyle(), false, selectedPerks));
    			
    			//list에 추가
    			participantOfSpectatorDTOs.add(participantOfSpectatorDTO);
    			
    		}
    		
    		//list에 저장된 참가자들 정보 추가
    		spectatorMatchDTO.setParticipants(participantOfSpectatorDTOs);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	//System.out.println("end getSpectatorMatch");
    	
    	return spectatorMatchDTO;
    	
    }
   

    private static String get(String apiUrl) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            return (responseCode == HttpURLConnection.HTTP_OK) ? readBody(con.getInputStream()) : readBody(con.getErrorStream());
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }
    

    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }
    

    private static String readBody(InputStream body) {
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
    
    private SpellDTO getSpell(String id) {
    	
    	//System.out.println("getRune");
    	
    	SpellDTO spellDTO = new SpellDTO();
    	
    	String url = infoUrl + "/" + version + "/data/" + language + "/summoner.json";
    	String responseBody = get(url);
    	
    	try {
    		
    		JSONObject response = new JSONObject(responseBody);
    		JSONObject spell = response.getJSONObject("data").getJSONObject(id);
    		
    		spellDTO.setKey(spell.getInt("key"));
    		spellDTO.setName(spell.getString("name"));
    		spellDTO.setDescription(spell.getString("description"));
    		spellDTO.setTooltip(spell.getString("tooltip"));
    		spellDTO.setCooldownBurn(spell.getString("cooldownBurn"));
    		spellDTO.setRangeBurn(spell.getString("rangeBurn"));
    		spellDTO.setImage(spell.getJSONObject("image").getString("full"));
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	//System.out.println("end getRune");
    	
    	return spellDTO;
    	
    }
    
    private PerkDTO getPerk(long perkStyle, boolean isMain, Set<Long> selectdRunes) {
    	
    	//System.out.println("start getMainPerk");
    	
    	PerkDTO perkDTO = new PerkDTO();
    	
    	String url = infoUrl + "/" + version + "/data/" + language + "/runesReforged.json";
    	String responseBody = get(url);
    	
    	try {
    		
    		JSONArray response = new JSONArray(responseBody);
    		JSONObject perk = new JSONObject();
    		
    		for(int i = 0; i < response.length(); i++) {
    			
    			JSONObject obj = response.getJSONObject(i);
    			if(obj.getLong("id") == perkStyle)	{ 
    				perk = obj;
    				break;
    			}
    			
    		}
    		
    		JSONArray slots = perk.getJSONArray("slots");
    		
    		//룬 기본정보
    		perkDTO.setId(perk.getLong("id"));
    		perkDTO.setIcon(perk.getString("icon"));
    		perkDTO.setName(perk.getString("name"));
    		
    		List<List<RuneDTO>> runesSet = new ArrayList<>();
    		
    		for(int i = 0; i < slots.length(); i++) {
    			
    			if(!isMain & i == 0)	continue;
    			
    			JSONArray runes = slots.getJSONObject(i).getJSONArray("runes");
    			List<RuneDTO> runesInfo = new ArrayList<>();
    			
    			for(int j = 0; j < runes.length(); j++) {
    				
    				JSONObject rune = runes.getJSONObject(j);
    				RuneDTO runeDTO = new RuneDTO();
    				
    				runeDTO.setId(rune.getLong("id"));
    				if(selectdRunes.contains(runeDTO.getId()))	runeDTO.setSelected(true);
    				runeDTO.setIcon(rune.getString("icon"));
    				runeDTO.setName(rune.getString("name"));
    				runeDTO.setLongDesc(rune.getString("longDesc"));
    				
    				runesInfo.add(runeDTO);
    				
    			}
    			
    			runesSet.add(runesInfo);
    			
    		}
    		
    		perkDTO.setRunes(runesSet);
    		
    	}catch(Exception e) {
    		e.printStackTrace();
    	}
    	
    	//System.out.println("end getMainPerk");
    	
    	return perkDTO;
    	
    }

    private String getGameModeKo(String gameMode) {
        return switch (gameMode) {
            case "CLASSIC" -> "소환사의 협곡";
            case "ARAM" -> "칼바람 나락";
            case "URF" -> "우르프";
            case "ONEFORALL" -> "단일 챔피언 모드";
            case "PRACTICETOOL" -> "연습 모드";
            default -> "기타";
        };
    }
    

	@Override
	public Map<String, Map<String, List<MatchDetailDTO>>> getRecentMatchDetailCategorized(String gameName,
			String tagLine) {
		// TODO Auto-generated method stub
		return null;
	}

}