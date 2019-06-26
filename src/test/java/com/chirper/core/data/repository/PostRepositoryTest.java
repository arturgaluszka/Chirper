package com.chirper.core.data.repository;

import com.chirper.core.model.Post;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class PostRepositoryTest {

    private static final String TEST_USER = "testUser";
    private static final String SECOND_USER = "testUserSecond";

    @Test
    public void findInEmptyRepository() {
        // given
        PostRepository postRepository = new PostRepository();

        // when
        List<Post> usersFound = postRepository.findByUser("testUser");

        // then
        assertThat(usersFound).isEmpty();
    }

    @Test
    public void savePost() {
        // given
        PostRepository postRepository = new PostRepository();
        Post toSave = testPost();

        // when
        Post saved = postRepository.save(toSave);

        // then
        assertThat(saved).isEqualTo(toSave);
        assertThat(postRepository.findByUser(TEST_USER)).containsOnly(toSave);
    }

    @Test
    public void saveForCorrectUser() {
        // given
        PostRepository postRepository = new PostRepository();
        Post firstPost = testPost();
        Post secondPost = testPostSecondUser();

        // when
        Post savedFirstPost = postRepository.save(firstPost);
        Post savedSecondPost = postRepository.save(secondPost);

        // then
        assertThat(savedFirstPost.getUser()).isEqualTo(firstPost.getUser());
        assertThat(savedSecondPost.getUser()).isEqualTo(secondPost.getUser());
    }

    private Post testPost() {
        return new Post(TEST_USER, "testMessage", new Date());
    }

    private Post testPostSecondUser() {
        return new Post(SECOND_USER, "testMessage", new Date());
    }


}