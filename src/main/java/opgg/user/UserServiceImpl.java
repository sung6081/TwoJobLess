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

    @Autowired
    private EmailVerificationService emailVerificationService;

    @Override
    public void addUser(UserDTO userDTO) {
        if (!emailVerificationService.isEmailVerified(userDTO.getEmail())) {
            throw new RuntimeException("이메일 인증이 완료되지 않았습니다.");
        }

        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new RuntimeException("이미 존재하는 이메일입니다.");
        }

        User user = User.builder()
                .name(userDTO.getName())
                .password(userDTO.getPassword())
                .cellPhone(userDTO.getCellPhone())
                .email(userDTO.getEmail())
                .isVerified(true)
                .build();

        userRepository.save(user);
        emailVerificationService.clearVerification(userDTO.getEmail());
    }

    @Override
    public boolean isEmailDuplicate(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public void resendVerificationEmail(String email) {
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("해당 이메일의 사용자가 존재하지 않습니다.");
        }

        User user = optionalUser.get();

        if (user.isVerified()) {
            throw new RuntimeException("이미 인증된 사용자입니다.");
        }

        String newToken = UUID.randomUUID().toString();
        emailVerificationService.saveToken(email, newToken);
        emailService.sendVerificationEmail(user.getEmail(), newToken);
    }

    @Override
    public UserDTO getUser(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다. ID: " + id));
        return toDTO(user);
    }

    @Override
    public List<UserDTO> getUserList() {
        return userRepository.findAll()
                .stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

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

        if (!user.isVerified()) {
            throw new RuntimeException("이메일 인증이 완료되지 않았습니다.");
        }

        return toDTO(user);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void sendVerificationEmailOnly(String email) {
        String token = UUID.randomUUID().toString();
        emailVerificationService.saveToken(email, token);
        emailService.sendVerificationEmail(email, token);
    }

    private UserDTO toDTO(User user) {
        return new UserDTO(
                user.getId(),
                user.getName(),
                user.getPassword(),
                user.getCellPhone(),
                user.getEmail(),
                user.getRegDate()
        );
    }
}