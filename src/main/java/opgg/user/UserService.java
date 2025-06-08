package opgg.user;

import java.util.List;
import opgg.dto.UserDTO;

public interface UserService {

    void addUser(UserDTO userDTO);

    UserDTO getUser(Long id);

    List<UserDTO> getUserList();
    
    UserDTO login(String email, String password);

    void deleteUser(Long id);
    
    
}
