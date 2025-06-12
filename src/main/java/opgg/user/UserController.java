package opgg.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import opgg.dto.UserDTO;
import opgg.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    // 회원가입
    @PostMapping("/addUser")
    public String addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return "회원가입 성공! 이메일 인증을 확인해 주세요.";
    }

    // 유저 1명 조회
    @PostMapping("/getUser")
    public ResponseEntity<UserDTO> getUser(@RequestBody Long id) {
        UserDTO userDTO = userService.getUser(id);
        return ResponseEntity.ok(userDTO);
    }

    // 유저 전체 조회
    @GetMapping("/getUserList")
    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }

    // 로그인
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
        try {
            UserDTO loginUser = userService.login(userDTO.getEmail(), userDTO.getPassword());
            return ResponseEntity.ok(loginUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // 이메일 인증
    @GetMapping("/verify")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        return userRepository.findByVerifyToken(token)
                .map(user -> {
                    user.setVerified(true);
                    user.setVerifyToken(null);
                    userRepository.save(user);
                    return ResponseEntity.ok("이메일 인증이 완료되었습니다.");
                })
                .orElse(ResponseEntity.badRequest().body("유효하지 않은 인증 토큰입니다."));
    }
}
