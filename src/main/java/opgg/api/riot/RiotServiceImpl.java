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
import java.util.List;
import java.util.Map;

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
import opgg.dto.RankDTO;
import opgg.dto.RiotAccountDTO;

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
    public Map<String, String> getNameAndKeyMapping() {
        System.out.println("start getNameANdKeyMapping api");
        String championsUrl = infoUrl + "/" + version + "/data/" + language + "/champion.json";
        System.out.println("getChampionName url : " + championsUrl);

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

        System.out.println("end getNameANdKeyMapping api");
        return mappingIdWithKey;
    }

    @Override
    public RiotChampion getChampion(int key, Map<String, String> mappingIdWithKey) {
        RiotChampion riotChampion = new RiotChampion();
        String name = mappingIdWithKey.get(Integer.toString(key));
        String championUrl = infoUrl + "/" + version + "/data/" + language + "/champion/" + name + ".json";

        try {
            JSONObject champion = new JSONObject(get(championUrl)).getJSONObject("data").getJSONObject(name);
            JSONObject passive = champion.getJSONObject("passive");
            JSONArray skills = champion.getJSONArray("spells");

            riotChampion.setId(champion.getString("id"));
            riotChampion.setKey(key);
            riotChampion.setName(champion.getString("name"));
            riotChampion.setTitle(champion.getString("title"));
            riotChampion.setImage(champion.getJSONObject("image").getString("full"));
            riotChampion.setLore(champion.getString("lore"));
            riotChampion.setPassive(passive.getString("name"));
            riotChampion.setPassiveDescription(passive.getString("description"));
            riotChampion.setPassiveImage(passive.getJSONObject("image").getString("full"));

            List<Skill> list = new ArrayList<>();
            for (int i = 0; i < skills.length(); i++) {
                JSONObject jsonObject = skills.getJSONObject(i);
                Skill skill = new Skill();

                skill.setName(jsonObject.getString("name"));
                skill.setDescription(jsonObject.getString("description"));
                skill.setTooltip(jsonObject.getString("tooltip"));
                skill.setCostType(jsonObject.getString("costType"));

                if (!skill.getCostType().equals("소모값 없음")) {
                    JSONArray array = jsonObject.getJSONArray("cost");
                    List<Integer> list2 = new ArrayList<>();
                    for (int j = 0; j < array.length(); j++) list2.add(array.getInt(j));
                    skill.setCost(list2);
                }

                JSONArray rangeArray = jsonObject.getJSONArray("range");
                List<Integer> rangeList = new ArrayList<>();
                for (int j = 0; j < rangeArray.length(); j++) rangeList.add(rangeArray.getInt(j));
                skill.setRange(rangeList);

                skill.setImage(jsonObject.getJSONObject("image").getString("full"));
                list.add(skill);
            }
            riotChampion.setSkills(list);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return riotChampion;
    }

    @Override
    public List<RiotChampion> getRotationChamps(Map<String, String> mappingIdWithKey) {
        List<RiotChampion> list = new ArrayList<>();
        System.out.println("start getRotationChamps api");

        String url = "https://kr.api.riotgames.com/lol/platform/v3/champion-rotations?api_key=" + apiKey;
        String rotationResponse = get(url);

        try {
            JSONObject rotationInfo = new JSONObject(rotationResponse);
            JSONArray rotation = rotationInfo.getJSONArray("freeChampionIds");
            for (int i = 0; i < rotation.length(); i++) {
                RiotChampion champ = getChampion(rotation.getInt(i), mappingIdWithKey);
                list.add(champ);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end getRotationChamps api");
        return list;
    }

    @Override
    public List<ChampionMastery> getMasteryWithGameName(String puuid, Map<String, String> mappingIdWithKey) {
        System.out.println("start getMasteryWithGameName api");

        String url = "https://kr.api.riotgames.com/lol/champion-mastery/v4/champion-masteries/by-puuid/" + puuid + "?api_key=" + apiKey;
        String responseBody = get(url);
        JSONArray masteries = new JSONArray(responseBody);
        List<ChampionMastery> result = new ArrayList<>();

        for (int i = 0; i < masteries.length(); i++) {
            JSONObject mastery = masteries.getJSONObject(i);
            ChampionMastery cm = new ChampionMastery();
            cm.setChampion(getChampion(mastery.getInt("championId"), mappingIdWithKey));
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

        System.out.println("end getMasteryWithGameName api");
        return result;
    }

    @Override
    public RiotAccountDTO getRiotAccountWithGameName(String gameName, String tagLine) {
        RiotAccountDTO dto = new RiotAccountDTO();
        System.out.println("start getRiotAccountWithGameName api");

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

        System.out.println("end getRiotAccountWithGameName api");
        return dto;
    }

    @Override
    public RiotAccountDTO getRiotAccountWithPuuid(String puuid) {
        System.out.println("start getRiotAccountWithPuuid");
        RiotAccountDTO dto = new RiotAccountDTO();
        String url = "https://asia.api.riotgames.com/riot/account/v1/accounts/by-puuid/" + puuid + "?api_key=" + apiKey;
        String responseBody = get(url);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            dto = objectMapper.readValue(responseBody, RiotAccountDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end getRiotAccountWithPuuid");
        return dto;
    }

    @Override
    public RiotAccountDTO getSummonerByPuuid(RiotAccountDTO riotAccountDTO) {
        System.out.println("start getSummonerByPuuid");

        String url = "https://kr.api.riotgames.com/lol/summoner/v4/summoners/by-puuid/" + riotAccountDTO.getPuuid() + "?api_key=" + apiKey;
        String responseBody = get(url);

        if (responseBody.contains("\"status\"")) {
            System.out.println("API 오류 응답: " + responseBody);
            return riotAccountDTO;
        }

        try {
            JSONObject summonerInfo = new JSONObject(responseBody);
            System.out.println(summonerInfo);
            //riotAccountDTO.setId(summonerInfo.getString("id"));
            riotAccountDTO.setProfileIconId(summonerInfo.getLong("profileIconId"));
            riotAccountDTO.setSummonerLevel(summonerInfo.getLong("summonerLevel"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("end getSummonerByPuuid");
        return riotAccountDTO;
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
        String url = "https://asia.api.riotgames.com/lol/match/v5/matches/by-puuid/" + puuid + "/ids?start=0&count=5&api_key=" + apiKey;
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
            JsonNode participants = info.path("participants");

            for (JsonNode participant : participants) {
                if (participant.path("puuid").asText().equals(puuid)) {
                    MatchDetailDTO dto = new MatchDetailDTO();
                    dto.setMatchId(matchId);
                    dto.setGameMode(info.path("gameMode").asText());
                    dto.setGameDuration(info.path("gameDuration").asLong());
                    dto.setChampionName(participant.path("championName").asText());
                    dto.setKills(participant.path("kills").asInt());
                    dto.setDeaths(participant.path("deaths").asInt());
                    dto.setAssists(participant.path("assists").asInt());
                    dto.setWin(participant.path("win").asBoolean());
                    dto.setSummonerName(participant.path("summonerName").asText());
                    return dto;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
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

    @Override
    public List<RankDTO> getRankByPuuid(String puuid) {
        System.out.println("start getRankByPuuid");

        List<RankDTO> ranks = new ArrayList<>();
        String url = "https://kr.api.riotgames.com/lol/league/v4/entries/by-puuid/"
                   + puuid + "?api_key=" + apiKey;

        try {
            String responseBody = get(url);

            // Riot API 오류 응답 처리
            if (responseBody.contains("\"status\"")) {
                System.out.println("API 오류 응답: " + responseBody);
                return ranks;
            }

            JSONArray rankArray = new JSONArray(responseBody);
            for (int i = 0; i < rankArray.length(); i++) {
                JSONObject obj = rankArray.getJSONObject(i);

                RankDTO dto = new RankDTO();
                dto.setQueueType(obj.optString("queueType"));
                dto.setTier(obj.optString("tier"));
                dto.setRank(obj.optString("rank"));
                dto.setLeaguePoints(obj.optInt("leaguePoints"));
                dto.setWins(obj.optInt("wins"));
                dto.setLosses(obj.optInt("losses"));

                ranks.add(dto);
            }
        } catch (Exception e) {
            System.out.println("랭크 정보 가져오기 실패");
            e.printStackTrace();
        }

        System.out.println("end getRankByPuuid");
        return ranks;
    }

}
