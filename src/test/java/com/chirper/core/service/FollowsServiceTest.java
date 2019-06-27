package com.chirper.core.service;

import com.chirper.core.data.repository.FollowsRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FollowsServiceTest {

    private static final String TEST_USER = "testUser";

    @Mock
    private FollowsRepository followsRepository;

    private FollowsService followsService;

    @Before
    public void setUp() {
        followsService = new FollowsService(followsRepository);
    }

    @Test
    public void findFollowsForEmptyUser() {
        // given

        // when
        List<String> userFollows = followsService.findForUser("testUser");

        // then
        assertThat(userFollows).isEmpty();
    }

    @Test
    public void findFollowsForNonEmptyUser() {
        // given
        String repositoryFollows = "toFollow";
        when(followsRepository.findByUser(TEST_USER)).thenReturn(List.of(repositoryFollows));

        // when
        List<String> userFollows = followsService.findForUser("testUser");

        // then
        assertThat(userFollows).hasSameElementsAs(List.of(repositoryFollows));
    }

    @Test
    public void addFollow() {
        // given
        String toFollow = "toFollow";
        when(followsRepository.save(TEST_USER, toFollow)).thenReturn(toFollow);

        // when
        String followed = followsService.addFollow(TEST_USER, toFollow);

        // then
        assertThat(followed).isEqualTo(toFollow);
    }
}