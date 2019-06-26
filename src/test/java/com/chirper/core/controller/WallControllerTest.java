package com.chirper.core.controller;

import com.chirper.core.model.Post;
import com.chirper.core.model.Wall;
import com.chirper.core.service.PostService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static java.util.Collections.emptyMap;
import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class WallControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private PostService postService;

    @Before
    public void setup() {
        postService.addPost(new Post("testUser", "testMessage", null));
    }

    @Test
    public void getExistingWall() {
        // given

        // when
        Wall response = restTemplate.getForObject("/wall/testUser", Wall.class, emptyMap());

        // then
        assertThat(response.getPosts()).hasSize(1)
                .allSatisfy(post ->
                        assertThat(post.getUser()).isEqualTo("testUser"));
    }
}