package opgg.user;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import opgg.dto.UserDTO;
import opgg.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private EmailVerificationService emailVerificationService;

    @PostMapping("/addUser")
    public String addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return "회원가입 성공! 이메일 인증을 확인해 주세요.";
    }

    @PostMapping("/getUser")
    public ResponseEntity<UserDTO> getUser(@RequestBody Long id) {
        UserDTO userDTO = userService.getUser(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping("/getUserList")
    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO, HttpSession session) {
        try {
            UserDTO loginUser = userService.login(userDTO.getEmail(), userDTO.getPassword());
            session.setAttribute("user", loginUser);
            return ResponseEntity.ok(loginUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        boolean verified = emailVerificationService.verifyToken(token);
        if (verified) {
            return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
        } else {
            return ResponseEntity.badRequest().body("유효하지 않은 인증 토큰입니다.");
        }
    }

    @GetMapping("/checkEmail")
    public ResponseEntity<Boolean> checkEmail(@RequestParam String email) {
        boolean isDuplicate = userService.isEmailDuplicate(email);
        return ResponseEntity.ok(isDuplicate);
    }

    @PostMapping("/resendVerification")
    public ResponseEntity<String> resendVerification(@RequestParam String email) {
        try {
            userService.resendVerificationEmail(email);
            return ResponseEntity.ok("인증 메일이 재발송되었습니다.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("로그아웃 완료");
    }

    @PostMapping("/sendVerification")
    public ResponseEntity<String> sendVerification(@RequestBody Map<String, String> request) {
        String email = request.get("email");
        try {
            userService.sendVerificationEmailOnly(email);
            return ResponseEntity.ok("인증 메일이 전송되었습니다.");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("인증 메일 전송 실패: " + e.getMessage());
        }
    }
}