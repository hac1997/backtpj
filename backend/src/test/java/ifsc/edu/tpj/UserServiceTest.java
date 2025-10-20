package ifsc.edu.tpj;

import ifsc.edu.tpj.dto.UserRequestDTO;
import ifsc.edu.tpj.model.User;
import ifsc.edu.tpj.repository.UserRepository;
import ifsc.edu.tpj.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    private UserRequestDTO testUserDTO;

    @BeforeEach
    void setUp() {
        // Clear any existing data
        userRepository.deleteAll();

        testUserDTO = new UserRequestDTO(
                "Test User",
                "jessericardorogerio@gmail.com",
                "SecurePass123!"
        );
    }

    @Test
    void createUserAccount_ShouldCreateUserAndSendVerificationEmail() {
        // Act
        User createdUser = userService.createUserAccount(testUserDTO);

        // Assert
        assertNotNull(createdUser);
        assertNotNull(createdUser.getUserId());
        assertEquals("Test User", createdUser.getName());
        assertEquals("jessericardorogerio@gmail.com", createdUser.getEmail());
        assertEquals("SecurePass123!", createdUser.getPassword());
        assertFalse(createdUser.isEmailVerified());
        assertNotNull(createdUser.getEmailVerificationCode());
        assertEquals(6, createdUser.getEmailVerificationCode().length());
    }

    @Test
    void verifyUserEmail_WithValidCode_ShouldVerifyEmail() {
        // Arrange
        User createdUser = userService.createUserAccount(testUserDTO);
        String verificationCode = createdUser.getEmailVerificationCode();

        // Act
        Boolean result = userService.verifyUserEmail("jessericardorogerio@gmail.com", verificationCode);

        // Assert
        assertTrue(result);
        
        User verifiedUser = userService.findUserByEmail("jessericardorogerio@gmail.com");
        assertTrue(verifiedUser.isEmailVerified());
        assertNull(verifiedUser.getEmailVerificationCode());
    }

    @Test
    void verifyUserEmail_WithInvalidCode_ShouldReturnFalse() {
        // Arrange
        userService.createUserAccount(testUserDTO);

        // Act
        Boolean result = userService.verifyUserEmail("jessericardorogerio@gmail.com", "INVALID");

        // Assert
        assertFalse(result);
        
        User user = userService.findUserByEmail("jessericardorogerio@gmail.com");
        assertFalse(user.isEmailVerified());
        assertNotNull(user.getEmailVerificationCode());
    }

    @Test
    void findUserById_WithExistingUser_ShouldReturnUser() {
        // Arrange
        User createdUser = userService.createUserAccount(testUserDTO);

        // Act
        User foundUser = userService.findUserById(createdUser.getUserId());

        // Assert
        assertNotNull(foundUser);
        assertEquals(createdUser.getUserId(), foundUser.getUserId());
        assertEquals(createdUser.getName(), foundUser.getName());
    }

    @Test
    void findUserById_WithNonExistingUser_ShouldThrowException() {
        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.findUserById(999L));
    }

    @Test
    void findUserByEmail_WithExistingUser_ShouldReturnUser() {
        // Arrange
        userService.createUserAccount(testUserDTO);

        // Act
        User foundUser = userService.findUserByEmail("jessericardorogerio@gmail.com");

        // Assert
        assertNotNull(foundUser);
        assertEquals("jessericardorogerio@gmail.com", foundUser.getEmail());
    }

    @Test
    void findUserByEmail_WithNonExistingUser_ShouldThrowException() {
        // Act & Assert
        assertThrows(RuntimeException.class, () -> userService.findUserByEmail("nonexisting@example.com"));
    }
}