package ifsc.edu.tpj.service;

import ifsc.edu.tpj.model.User;
import ifsc.edu.tpj.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    protected User findUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("USER NOT FOUND WITH ID :: "+userId));
    }
}
