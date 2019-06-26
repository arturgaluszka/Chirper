package com.chirper.core.service;

import com.chirper.core.data.repository.PostRepository;
import com.chirper.core.model.Post;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {

    private static final String TEST_USER = "testUser";

    @Mock
    private PostRepository postRepository;

    private PostService postService;

    @Before
    public void setUp() {
        postService = new PostService(postRepository);
    }

    @Test
    public void findPostsForEmptyUser() {
        // given

        // when
        List<Post> userPosts = postService.findForUser("testUser");

        // then
        assertThat(userPosts).isEmpty();
    }

    @Test
    public void findPostsForNonEmptyUser() {
        // given
        Post repositoryPost = testPost();
        when(postRepository.findByUser("testUser")).thenReturn(List.of(repositoryPost));

        // when
        List<Post> userPosts = postService.findForUser("testUser");

        // then
        assertThat(userPosts).hasSameElementsAs(List.of(repositoryPost));
    }

    @Test
    public void addPost() {
        // given
        Post post = testPost();
        when(postRepository.save(any(Post.class))).thenReturn(post);

        // when
        Post posted = postService.addPost(new Post(post.getUser(), post.getMessage(), null));

        // then
        assertThat(posted).isEqualTo(post);
    }

    private Post testPost() {
        return new Post(TEST_USER, "testMessage", new Date());
    }
}