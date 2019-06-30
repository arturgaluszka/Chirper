package com.chirper.core.data.repository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class FollowsRepository {
    private final Map<String, List<String>> follows;

    public FollowsRepository() {
        log.info("initializing follows repository");
        follows = new HashMap<>();
    }

    public List<String> findByUser(String user) {
        List<String> userFollows = this.follows.get(user);
        if (userFollows == null) return new ArrayList<>();
        log.info("Found {} follows for user {}", userFollows.size(), user);
        return userFollows;
    }

    public String save(String user, String toFollow) {
        follows.putIfAbsent(user, new ArrayList<>());
        follows.get(user).add(toFollow);
        log.info("Saving follow from {} to {}", user, toFollow);
        return toFollow;
    }
}
