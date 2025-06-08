package opgg.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class KakaoConfig {

    @Value("${kakao.rest-api-key}")
    private String restApiKey;

    @Value("${kakao.javascript-key}")
    private String javascriptKey;

    @Value("${kakao.redirect-uri}")
    private String redirectUri;

    public String getRestApiKey() {
        return restApiKey;
    }

    public String getJavascriptKey() {
        return javascriptKey;
    }

    public String getRedirectUri() {
        return redirectUri;
    }
}
