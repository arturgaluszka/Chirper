package com.chirper.core.service;

import com.chirper.core.model.Post;
import com.chirper.core.model.Wall;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        assertThat(testUserWall.getTimestamp()).isBeforeOrEqualsTo(new Date());
    }

    @Test
    public void userWallIsOrdered() {
        // given
        when(postService.findForUser(TEST_USER)).thenReturn(postsOneHourApart());

        // when
        Wall testUserWall = wallService.findByUser(TEST_USER);

        // then
        assertThat(testUserWall.getPosts()).hasSize(2);
        Post firstPost = testUserWall.getPosts().get(0);
        Post secondPost = testUserWall.getPosts().get(1);
        assertThat(firstPost.getTimestamp()).isAfter(secondPost.getTimestamp());
    }

    private List<Post> postsOneHourApart() {
        Post earlierPost = testPostWithDate(new Date());
        Post laterPost = testPostWithDate(DateUtils.addHours(new Date(), 1));
        return new ArrayList<>(List.of(earlierPost, laterPost));
    }

    private Post testPost() {
        return testPostWithDate(new Date());
    }

    private Post testPostWithDate(Date date) {
        return new Post(TEST_USER, "testMessage", date);
    }
}