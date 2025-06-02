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

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import opgg.dto.RiotAccountDTO;

@Service("riotService")
public class RiotServiceImpl implements RiotService {

	//Field
	@Value("${riot.apiKey}")
	private String apiKey;
	
	@Override
	public RiotAccountDTO getRiotAccountWithGameName(String gameName, String tagLine) {
		// TODO Auto-generated method stub
		RiotAccountDTO riotAccountDTO = new RiotAccountDTO();
		
		System.out.println("start api");
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
		System.out.println(responseBody);
		
		ObjectMapper objectMapper = new ObjectMapper();
		
		try {
            riotAccountDTO = objectMapper.readValue(responseBody, RiotAccountDTO.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		System.out.println("end api");
		
		return riotAccountDTO;
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
