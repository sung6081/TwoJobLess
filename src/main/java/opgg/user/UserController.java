package opgg.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import opgg.dto.UserDTO;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입 API
    @PostMapping("/add")
    public String addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return "회원가입 성공!";
    }
}
