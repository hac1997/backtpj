package ifsc.edu.tpj;

import ifsc.edu.tpj.dto.PostRequestDTO;
import ifsc.edu.tpj.dto.UserRequestDTO;
import ifsc.edu.tpj.model.Post;
import ifsc.edu.tpj.model.User;
import ifsc.edu.tpj.repository.PostRepository;
import ifsc.edu.tpj.repository.UserRepository;
import ifsc.edu.tpj.service.PostService;
import ifsc.edu.tpj.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
class PostServiceTest {

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private PostRequestDTO testPostDTO;

    @BeforeEach
    void setUp() {
        // Clear any existing data
        postRepository.deleteAll();
        userRepository.deleteAll();

        // Create a test user
        testUser = userService.createUserAccount(new UserRequestDTO(
                "Post Author",
                "author@example.com",
                "SecurePass123!"
        ));

        testPostDTO = new PostRequestDTO(
                "Test Post Title",
                "This is the body of the test post",
                List.of("java", "spring", "testing"),
                testUser.getUserId()
        );
    }

    @Test
    void create_ShouldCreatePostSuccessfully() {
        // Act
        Post createdPost = postService.create(testPostDTO);

        // Assert
        assertNotNull(createdPost);
        assertNotNull(createdPost.getPostId());
        assertEquals("Test Post Title", createdPost.getTitle());
        assertEquals("This is the body of the test post", createdPost.getBody());
        assertEquals(List.of("java", "spring", "testing"), createdPost.getTags());
        assertEquals(testUser.getUserId(), createdPost.getAuthor().getUserId());
        assertNotNull(createdPost.getCreatedAt());
    }

    @Test
    void findById_WithExistingPost_ShouldReturnPost() {
        // Arrange
        Post createdPost = postService.create(testPostDTO);

        // Act
        Post foundPost = postService.findById(createdPost.getPostId());

        // Assert
        assertNotNull(foundPost);
        assertEquals(createdPost.getPostId(), foundPost.getPostId());
        assertEquals(createdPost.getTitle(), foundPost.getTitle());
    }

    @Test
    void findById_WithNonExistingPost_ShouldThrowException() {
        // Act & Assert
        assertThrows(RuntimeException.class, () -> postService.findById(999L));
    }

    @Test
    void update_ShouldUpdatePostSuccessfully() {
        // Arrange
        Post createdPost = postService.create(testPostDTO);
        PostRequestDTO updateDTO = new PostRequestDTO(
                "Updated Title",
                "Updated body content",
                List.of("updated", "tags"),
                testUser.getUserId()
        );

        // Act
        Post updatedPost = postService.update(createdPost.getPostId(), updateDTO);

        // Assert
        assertNotNull(updatedPost);
        assertEquals("Updated Title", updatedPost.getTitle());
        assertEquals("Updated body content", updatedPost.getBody());
        assertEquals(List.of("updated", "tags"), updatedPost.getTags());
        assertEquals(createdPost.getPostId(), updatedPost.getPostId());
    }

    @Test
    void update_WithDifferentUser_ShouldThrowException() {
        // Arrange
        Post createdPost = postService.create(testPostDTO);
        
        // Create another user
        User anotherUser = userService.createUserAccount(new UserRequestDTO(
                "Another User",
                "another@example.com",
                "SecurePass123!"
        ));

        PostRequestDTO updateDTO = new PostRequestDTO(
                "Updated Title",
                "Updated body",
                List.of("tag"),
                anotherUser.getUserId()
        );

        // Act & Assert
        assertThrows(RuntimeException.class, 
            () -> postService.update(createdPost.getPostId(), updateDTO));
    }

    @Test
    void delete_ShouldDeletePostSuccessfully() {
        // Arrange
        Post createdPost = postService.create(testPostDTO);
        Long postId = createdPost.getPostId();

        // Act
        Post deletedPost = postService.delete(postId);

        // Assert
        assertNotNull(deletedPost);
        assertEquals(postId, deletedPost.getPostId());
        assertThrows(RuntimeException.class, () -> postService.findById(postId));
    }
}