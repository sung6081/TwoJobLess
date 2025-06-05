package opgg.user;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
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

    @Autowired
    private EmailService emailService;

    // 회원가입
    @Override
    public void addUser(UserDTO userDTO) {
        String token = UUID.randomUUID().toString();

        User user = User.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword()) // 평문 그대로 저장
                .cellPhone(userDTO.getCellPhone())
                .email(userDTO.getEmail())
                .verifyToken(token)
                .isVerified(false)
                .build();

        userRepository.save(user);
        emailService.sendVerificationEmail(user.getEmail(), token);
    }

    // 유저 한 명 조회
    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. ID: " + id));
        return toDTO(user);
    }

    // 유저 전체 목록 조회
    @Override
    public List<UserDTO> getUserList() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // 로그인
    @Override
    public UserDTO login(String email, String password) {
        Optional<User> optionalUser = userRepository.findByEmail(email);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("존재하지 않는 이메일입니다.");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return toDTO(user);
    }

    // 유저 삭제 (옵션)
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // 내부 변환 메서드
    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getPassword(), // 그대로 반환
                user.getCellPhone(),
                user.getEmail(),
                user.getRegDate()
        );
    }
}
