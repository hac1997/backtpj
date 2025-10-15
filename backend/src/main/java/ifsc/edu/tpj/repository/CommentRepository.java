package ifsc.edu.tpj.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import ifsc.edu.tpj.model.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_PostId(Long postId);
}
