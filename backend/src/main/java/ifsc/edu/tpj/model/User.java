package ifsc.edu.tpj.model;

import ifsc.edu.tpj.util.ValidPasswd;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false, unique = true)
    private String email;

    @ValidPasswd
    @Column(nullable = false)
    private String password;

    private boolean isEmailVerified;
    private String emailVerificationCode;


    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Post> posts;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comment> comments;
}
