package com.chirper.core.data.repository;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class FollowsRepositoryTest {

    private static final String TEST_USER = "testUser";
    private static final String SECOND_USER = "testUserSecond";

    @Test
    public void findInEmptyRepository() {
        // given
        FollowsRepository followsRepository = new FollowsRepository();

        // when
        List<String> followsFound = followsRepository.findByUser("testUser");

        // then
        assertThat(followsFound).isEmpty();
    }

    @Test
    public void savePost() {
        // given
        FollowsRepository followsRepository = new FollowsRepository();
        String toSave = "toFollow";

        // when
        String saved = followsRepository.save(TEST_USER, toSave);

        // then
        assertThat(saved).isEqualTo(toSave);
        assertThat(followsRepository.findByUser(TEST_USER)).containsOnly(toSave);
    }

    @Test
    public void saveForCorrectUser() {
        // given
        FollowsRepository followsRepository = new FollowsRepository();
        String firstFollow = "firstFollow";
        String secondFollow = "secondFollow";

        // when
        String savedFirstFollow = followsRepository.save(TEST_USER, firstFollow);
        String savedSecondFollow = followsRepository.save(SECOND_USER, secondFollow);

        // then
        assertThat(savedFirstFollow).isEqualTo(firstFollow);
        assertThat(savedSecondFollow).isEqualTo(secondFollow);
    }
}