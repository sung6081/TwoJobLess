package opgg.api.google;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import opgg.dto.GoogleDTO;
import opgg.entity.GoogleUser;
import opgg.repository.GoogleRepository;
import opgg.user.GoogleService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/oauth")
public class GoogleLoginRestController {
	
	//Filed
	@Autowired
	@Qualifier("googleService")
	GoogleService googleService;
	
	@Autowired
	GoogleRepository googleRepository;
	
	@RequestMapping("/google")
	public String googleLogin(@RequestParam("code") String code) {
		
		try {
			
			String accessToken = googleService.getAccessToken(code);
			
			GoogleDTO googleDTO = googleService.getUserInfo(accessToken);
			
			Optional<GoogleUser> optionalUser = googleRepository.findByEmail(googleDTO.getEmail());
			
			System.out.println(googleDTO);
			
			if(optionalUser.isPresent()) {
				return googleDTO.getEmail();
			}
			
			GoogleUser newUser = GoogleUser.of(googleDTO.getId(), googleDTO.getEmail(), googleDTO.getName(), googleDTO.getPicture());
			googleRepository.save(newUser);
			
			return googleDTO.getEmail();
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
		
	}
	
}
