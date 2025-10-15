package ifsc.edu.tpj.service;

import ifsc.edu.tpj.dto.CommentRequestDTO;
import ifsc.edu.tpj.model.Comment;
import ifsc.edu.tpj.model.Post;
import ifsc.edu.tpj.repository.CommentRepository;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
private final UserService userService;

    public Comment createComment(CommentRequestDTO dto){
        
        return commentRepository.save(Comment.builder()
                        .author(userService.findUserById(dto.userId()))
                        .body(dto.body())
                        .build());

    }

}
