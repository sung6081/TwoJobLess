package opgg.user;

import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmailVerificationService {

    private final Map<String, String> tokenStorage = new ConcurrentHashMap<>();
    private final Map<String, Boolean> verifiedEmails = new ConcurrentHashMap<>();

    public void saveToken(String email, String token) {
        tokenStorage.put(token, email);
    }

    public boolean verifyToken(String token) {
        String email = tokenStorage.remove(token);
        if (email != null) {
            verifiedEmails.put(email, true);
            return true;
        }
        return false;
    }

    public boolean isEmailVerified(String email) {
        return verifiedEmails.getOrDefault(email, false);
    }

    public void clearVerification(String email) {
        verifiedEmails.remove(email);
    }
}
