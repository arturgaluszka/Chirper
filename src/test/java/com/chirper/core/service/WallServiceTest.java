package com.chirper.core.service;

import com.chirper.core.model.Post;
import com.chirper.core.model.Wall;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Date;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WallServiceTest {

    private static final String TEST_USER = "testUser";
    @Mock
    private PostService postService;

    private WallService wallService;

    @Before
    public void setup() {
        wallService = new WallService(postService);
    }

    @Test
    public void findUserWall() {
        // given
        when(postService.findForUser(TEST_USER)).thenReturn(singletonList(testPost()));

        // when
        Wall testUserWall = wallService.findByUser(TEST_USER);

        // then
        assertThat(testUserWall.getPosts()).hasSize(1);
    }

    @Test
    public void usersWallTimestampIsInThePast() {
        // given
        when(postService.findForUser(TEST_USER)).thenReturn(emptyList());

        // when
        Wall testUserWall = wallService.findByUser(TEST_USER);

        // then
        assertThat(testUserWall.getTimestamp()).isBefore(new Date());
    }

    private Post testPost() {
        return new Post(TEST_USER, "testMessage", new Date());
    }
}