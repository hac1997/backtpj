package ifsc.edu.tpj;

import ifsc.edu.tpj.dto.CommentRequestDTO;
import ifsc.edu.tpj.dto.UserRequestDTO;
import ifsc.edu.tpj.model.Comment;
import ifsc.edu.tpj.model.Post;
import ifsc.edu.tpj.model.User;
import ifsc.edu.tpj.repository.CommentRepository;
import ifsc.edu.tpj.repository.PostRepository;
import ifsc.edu.tpj.repository.UserRepository;
import ifsc.edu.tpj.service.CommentService;
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
class CommentServiceTest  {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @Autowired
    private UserService userService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private User testUser;
    private User commentAuthor;
    private Post testPost;
    private CommentRequestDTO testCommentDTO;

    @BeforeEach
    void setUp() {
        // Clear any existing data
        commentRepository.deleteAll();
        postRepository.deleteAll();
        userRepository.deleteAll();

        // Create test users
        testUser = userService.createUserAccount(new UserRequestDTO(
                "Post Author",
                "author@example.com",
                "SecurePass123!"
        ));

        commentAuthor = userService.createUserAccount(new UserRequestDTO(
                "Comment Author",
                "commenter@example.com",
                "SecurePass123!"
        ));

        // Create a test post
        testPost = postService.create(new ifsc.edu.tpj.dto.PostRequestDTO(
                "Test Post",
                "Post content",
                List.of("test"),
                testUser.getUserId()
        ));

        testCommentDTO = new CommentRequestDTO(
                "This is a test comment",
                testPost.getPostId(),
                commentAuthor.getUserId()
        );
    }

    @Test
    void create_ShouldCreateCommentSuccessfully() {
        // Act
        Comment createdComment = commentService.create(testPost.getPostId(), testCommentDTO);

        // Assert
        assertNotNull(createdComment);
        assertNotNull(createdComment.getCommentId());
        assertEquals("This is a test comment", createdComment.getBody());
        assertEquals(commentAuthor.getUserId(), createdComment.getAuthor().getUserId());
        assertEquals(testPost.getPostId(), createdComment.getPost().getPostId());
        assertNotNull(createdComment.getCreatedAt());
    }

    @Test
    void create_WithNonExistingPost_ShouldThrowException() {
        // Arrange
        CommentRequestDTO invalidDTO = new CommentRequestDTO(
                "Test comment",
                999L, // Non-existing post ID
                commentAuthor.getUserId()
        );

        // Act & Assert
        assertThrows(RuntimeException.class, 
            () -> commentService.create(999L, invalidDTO));
    }

    @Test
    void findByPost_ShouldReturnCommentsForPost() {
        // Arrange
        commentService.create(testPost.getPostId(), testCommentDTO);
        
        // Create another comment
        CommentRequestDTO anotherCommentDTO = new CommentRequestDTO(
                "Another comment",
                testPost.getPostId(),
                commentAuthor.getUserId()
        );
        commentService.create(testPost.getPostId(), anotherCommentDTO);

        // Act
        List<Comment> comments = commentService.findByPost(testPost.getPostId());

        // Assert
        assertNotNull(comments);
        assertEquals(2, comments.size());
        assertTrue(comments.stream().anyMatch(c -> c.getBody().equals("This is a test comment")));
        assertTrue(comments.stream().anyMatch(c -> c.getBody().equals("Another comment")));
    }

    @Test
    void findByPost_WithNoComments_ShouldReturnEmptyList() {
        // Act
        List<Comment> comments = commentService.findByPost(testPost.getPostId());

        // Assert
        assertNotNull(comments);
        assertTrue(comments.isEmpty());
    }

    @Test
    void findById_WithExistingComment_ShouldReturnComment() {
        // Arrange
        Comment createdComment = commentService.create(testPost.getPostId(), testCommentDTO);

        // Act
        Comment foundComment = commentService.findById(createdComment.getCommentId());

        // Assert
        assertNotNull(foundComment);
        assertEquals(createdComment.getCommentId(), foundComment.getCommentId());
        assertEquals(createdComment.getBody(), foundComment.getBody());
    }

    @Test
    void findById_WithNonExistingComment_ShouldThrowException() {
        // Act & Assert
        assertThrows(RuntimeException.class, () -> commentService.findById(999L));
    }

    @Test
    void update_ShouldUpdateCommentSuccessfully() {
        // Arrange
        Comment createdComment = commentService.create(testPost.getPostId(), testCommentDTO);
        CommentRequestDTO updateDTO = new CommentRequestDTO(
                "Updated comment body",
                testPost.getPostId(),
                commentAuthor.getUserId()
        );

        // Act
        Comment updatedComment = commentService.update(createdComment.getCommentId(), updateDTO);

        // Assert
        assertNotNull(updatedComment);
        assertEquals("Updated comment body", updatedComment.getBody());
        assertEquals(createdComment.getCommentId(), updatedComment.getCommentId());
    }

    @Test
    void update_WithDifferentAuthor_ShouldThrowException() {
        // Arrange
        Comment createdComment = commentService.create(testPost.getPostId(), testCommentDTO);
        
        // Create another user
        User anotherUser = userService.createUserAccount(new UserRequestDTO(
                "Another User",
                "another@example.com",
                "SecurePass123!"
        ));

        CommentRequestDTO updateDTO = new CommentRequestDTO(
                "Updated comment",
                testPost.getPostId(),
                anotherUser.getUserId()
        );

        // Act & Assert
        assertThrows(RuntimeException.class, 
            () -> commentService.update(createdComment.getCommentId(), updateDTO));
    }

    @Test
    void delete_ShouldDeleteCommentSuccessfully() {
        // Arrange
        Comment createdComment = commentService.create(testPost.getPostId(), testCommentDTO);
        Long commentId = createdComment.getCommentId();

        // Act
        Comment deletedComment = commentService.delete(commentId);

        // Assert
        assertNotNull(deletedComment);
        assertEquals(commentId, deletedComment.getCommentId());
        assertThrows(RuntimeException.class, () -> commentService.findById(commentId));
    }
}