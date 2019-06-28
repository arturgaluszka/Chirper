package com.chirper.core.service;

import com.chirper.core.model.Post;
import com.chirper.core.model.TimeLine;
import com.chirper.core.model.Wall;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TimelineServiceTest {

    private static final String TEST_USER = "testUser";

    private TimelineService timelineService;

    @Mock
    private FollowsService followsService;

    @Mock
    private WallService wallService;

    @Before
    public void setup() {
        timelineService = new TimelineService(wallService, followsService);
    }

    @Test
    public void findForNonExistingUser() {
        // given

        // when
        TimeLine userTimeline = timelineService.findForUser(TEST_USER);

        // then
        assertThat(userTimeline.getPosts()).isEmpty();
    }

    @Test
    public void findForUserWithoutFollows() {
        // given
        when(followsService.findForUser(TEST_USER)).thenReturn(new ArrayList<>());

        // when
        TimeLine userTimeline = timelineService.findForUser(TEST_USER);

        // then
        assertThat(userTimeline.getPosts()).isEmpty();
    }

    @Test
    public void findForUserWithOneFollow() {
        // given
        Post post = new Post("follower", "testMessage", new Date());

        when(followsService.findForUser(TEST_USER)).thenReturn(List.of("follower"));
        when(wallService.findByUser("follower")).thenReturn(new Wall(List.of(post), new Date()));

        // when
        TimeLine userTimeline = timelineService.findForUser(TEST_USER);

        // then
        assertThat(userTimeline.getPosts()).containsExactly(post);
    }

    @Test
    public void findForUserWithMixingFollows() {
        // given
        Post firstPost = new Post("follower", "testMessage", new Date());
        Post secondPost = new Post("secondFollower", "testMessage", DateUtils.addHours(new Date(), 1));
        Post thirdPost = new Post("follower", "testMessage", DateUtils.addHours(new Date(), 2));

        when(followsService.findForUser(TEST_USER)).thenReturn(List.of("follower", "secondFollower"));
        when(wallService.findByUser("follower")).thenReturn(new Wall(List.of(thirdPost, firstPost), new Date()));
        when(wallService.findByUser("secondFollower")).thenReturn(new Wall(List.of(secondPost), new Date()));

        // when
        TimeLine userTimeLine = timelineService.findForUser(TEST_USER);

        // then
        assertThat(userTimeLine.getPosts()).containsExactly(thirdPost, secondPost, firstPost);
    }

}