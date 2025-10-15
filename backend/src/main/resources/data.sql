--
-- Table structure for table 'users'
-- Corresponds to the User.java entity
--


INSERT INTO users (user_id, name, email, password)
SELECT 1, 'Alice Johnson', 'alice.j@example.com', 'securepass123'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_id = 1);

INSERT INTO users (user_id, name, email, password)
SELECT 2, 'Bob Williams', 'bob.w@example.com', 'mypassword456'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_id = 2);

INSERT INTO users (user_id, name, email, password)
SELECT 3, 'Charlie Brown', 'charlie.b@example.com', 'testpassword789'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE user_id = 3);

INSERT INTO posts (post_id, title, body, created_at, user_id)
SELECT 101, 'Spring Boot Tips', 'Here are some quick tips for optimizing your Spring Boot application startup time.', CURRENT_TIMESTAMP, 1
WHERE NOT EXISTS (SELECT 1 FROM posts WHERE post_id = 101);

INSERT INTO posts (post_id, title, body, created_at, user_id)
SELECT 102, 'The Future of Java', 'Discussing Project Loom and other exciting features coming to the Java ecosystem.', CURRENT_TIMESTAMP, 2
WHERE NOT EXISTS (SELECT 1 FROM posts WHERE post_id = 102);

INSERT INTO posts (post_id, title, body, created_at, user_id)
SELECT 103, 'JPA Entity Mapping Basics', 'A guide to mapping basic fields, relationships, and auditing listeners.', CURRENT_TIMESTAMP, 1
WHERE NOT EXISTS (SELECT 1 FROM posts WHERE post_id = 103);

INSERT INTO comments (comment_id, body, created_at, post_id, user_id)
SELECT 501, 'Great tips, especially the lazy loading one!', CURRENT_TIMESTAMP, 101, 2
WHERE NOT EXISTS (SELECT 1 FROM comments WHERE comment_id = 501);

INSERT INTO comments (comment_id, body, created_at, post_id, user_id)
SELECT 502, 'I agree, Spring Boot is fantastic for quick setup.', CURRENT_TIMESTAMP, 101, 3
WHERE NOT EXISTS (SELECT 1 FROM comments WHERE comment_id = 502);

INSERT INTO comments (comment_id, body, created_at, post_id, user_id)
SELECT 503, 'Loom looks incredibly promising. Cannot wait for it to be mainstream.', CURRENT_TIMESTAMP, 102, 1
WHERE NOT EXISTS (SELECT 1 FROM comments WHERE comment_id = 503);

INSERT INTO comments (comment_id, body, created_at, post_id, user_id)
SELECT 504, 'Very clear explanation of the @JoinColumn annotation.', CURRENT_TIMESTAMP, 103, 2
WHERE NOT EXISTS (SELECT 1 FROM comments WHERE comment_id = 504);
