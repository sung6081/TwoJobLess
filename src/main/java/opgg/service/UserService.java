package opgg.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import opgg.dto.UserDTO;
import opgg.entity.User;
import opgg.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long registerUser(UserDTO userDTO) {
 
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new IllegalArgumentException("이미 존재하는 이메일입니다.");
        }

        User user = new User(
            null,
            userDTO.getName(),
            userDTO.getPassword(),
            userDTO.getCellPhone(),
            userDTO.getEmail(),
            null 
        );

        return userRepository.save(user).getId();
    }
}
