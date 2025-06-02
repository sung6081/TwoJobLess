package opgg.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import opgg.dto.UserDTO;
import opgg.entity.User;
import opgg.repository.UserRepository;

@Service("UserServiceImpl")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void addUser(UserDTO userDTO) {
        User user = User.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .cellPhone(userDTO.getCellPhone())
                .email(userDTO.getEmail())
                .build();
        userRepository.save(user); 
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. ID: " + id));

        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getCellPhone(),
                user.getEmail(),
                user.getRegDate()
        );
    }


    @Override
    public List<UserDTO> getUserList() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(user -> new UserDTO(
                        user.getId(),
                        user.getName(),
                        user.getPassword(),
                        user.getCellPhone(),
                        user.getEmail(),
                        user.getRegDate()
                ))
                .collect(Collectors.toList());
    }


    @Override
    public void deleteUser(Long id) {
    }
}
