package ifsc.edu.tpj;

import ifsc.edu.tpj.service.MailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class MailServiceTest {

    @Autowired
    private MailService mailService;

    @Test
    void sendMailMessage_ShouldSendEmailWithoutErrors() {
        // Arrange
        String subject = "Test Subject";
        String body = "Test email body content";
        String receiverAddress = "test@example.com";

        // Act & Assert - This should complete without throwing exceptions
        assertDoesNotThrow(() -> 
            mailService.sendMailMessage(subject, body, receiverAddress)
        );
    }
}