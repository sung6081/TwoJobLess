package opgg.user;

import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173") 
@RestController
@RequestMapping("/opgg/user")
public class UserController {

    @GetMapping("/test")
    public String test() {
        return "CORS 연결 성공!";
    }
}
