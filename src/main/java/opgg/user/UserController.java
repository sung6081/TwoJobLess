package opgg.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import opgg.dto.UserDTO;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입 API
    @PostMapping("/addUser")
    public String addUser(@RequestBody UserDTO userDTO) {
        userService.addUser(userDTO);
        return "회원가입 성공!";
    }
    
    @PostMapping("/getUser")
    public String getUser(@RequestBody Long id) {
        UserDTO userDTO = userService.getUser(id); 
        System.out.println(userDTO.toString());
        return userDTO.toString(); 
    }
    
    @GetMapping("/getUserList")
    public ResponseEntity<List<UserDTO>> getUserList() {
        List<UserDTO> userList = userService.getUserList();
        return ResponseEntity.ok(userList);
    }


}
