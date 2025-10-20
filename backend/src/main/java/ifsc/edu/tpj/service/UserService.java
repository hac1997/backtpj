package ifsc.edu.tpj.service;

import ifsc.edu.tpj.dto.UserRequestDTO;
import ifsc.edu.tpj.model.User;
import ifsc.edu.tpj.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final MailService mailService;

    public User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("USER NOT FOUND WITH ID :: "+userId));
    }

    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("USER NOT FOUND WITH EMAIL :: "+email));
    }

    public User createUserAccount(UserRequestDTO requestDTO) {
        String code = UUID.randomUUID().toString().substring(0, 6);

        var user = userRepository.save(User.builder()
                        .name(requestDTO.name())
                        .email(requestDTO.email())
                        .password(requestDTO.passwd())
                        .isEmailVerified(false)
                        .emailVerificationCode(code)
                .build());

        mailService.sendMailMessage(
                "Email Verification - TPJ",
                "Your verification code is: "+code,
                requestDTO.email()
        );
        return user;
    }

    public Boolean verifyUserEmail(String email, String code) {
        User user = this.findUserByEmail(email);

        if (user.getEmailVerificationCode().equals(code)) {
            user.setEmailVerified(true);
            user.setEmailVerificationCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}
