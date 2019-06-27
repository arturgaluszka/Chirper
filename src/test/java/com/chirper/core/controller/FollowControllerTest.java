package com.chirper.core.controller;

import com.chirper.core.service.FollowsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class FollowControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private FollowsService followsService;

    @Test
    public void post() {
        // given
        String secondUser = "toFollow";

        // when
        String posted = restTemplate.postForObject("/user/testuser/follows", secondUser, String.class);

        // then
        assertThat(posted).isEqualTo(secondUser);
        List<String> followedUsers = followsService.findForUser("testuser");
        assertThat(followedUsers).hasSameElementsAs(List.of(secondUser));
    }
}