package ifsc.edu.tpj.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ifsc.edu.tpj.model.Post;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
