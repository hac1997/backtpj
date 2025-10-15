package ifsc.edu.tpj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class TpjApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpjApplication.class, args);
    }

}
