package com.chirper.core.controller;

import com.chirper.core.model.Post;
import com.chirper.core.service.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class PostControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostService postService;

    @Test
    public void postTooLargeMessage() {
        // given
        Post newPost = new Post("testUser", longMessage(), null);

        // when
        ResponseEntity<Post> response = restTemplate.exchange("/post", HttpMethod.POST, new HttpEntity<>(newPost), Post.class);

        // then
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);

        assertThat(postService.findForUser("testUser")).isEmpty();
    }

    @Test
    public void post() {
        // given
        Post newPost = new Post("testUser", "testMessage", null);

        // when
        Post posted = restTemplate.postForObject("/post", newPost, Post.class);

        // then
        assertThat(posted.getUser()).isEqualTo("testUser");
        assertThat(posted.getMessage()).isEqualTo("testMessage");

        List<Post> testUser = postService.findForUser("testUser");
        assertThat(testUser).allSatisfy(post ->
                assertThat(post.getUser()).isEqualTo("testUser"));

        assertThat(testUser).allSatisfy(post ->
                assertThat(post.getMessage()).isEqualTo("testMessage"));
    }

    private String longMessage() {
        return "TestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTest" +
                "TestTestTestTestTestTestTestTestTestTestTestTest";
    }
}