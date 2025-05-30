package opgg.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import opgg.dto.UserDTO;
import opgg.entity.User;
import opgg.repository.UserRepository;
import opgg.user.UserService;

@Service("UserServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    @Qualifier("userRepository")
    private UserRepository userRepository;

    @Override
    public void addUser(UserDTO userDTO) throws Exception {
        User user = new User(
                userDTO.getId(),
                userDTO.getName(),
                userDTO.getPassword(),
                userDTO.getCellPhone(),
                userDTO.getEmail(),
                null 
        );
        userRepository.save(user);
    }

	@Override
	public UserDTO getUser(Long id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<UserDTO> getUserList() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		// TODO Auto-generated method stub
		
	}
}
