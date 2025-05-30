package opgg.user;

import java.util.List;
import opgg.dto.UserDTO;

public interface UserService {
	
    void addUser(UserDTO userDTO) throws Exception;
    
    UserDTO getUser(Long id) throws Exception;
    
    List<UserDTO> getUserList() throws Exception;
    
    void deleteUser(Long id) throws Exception;
    
}
