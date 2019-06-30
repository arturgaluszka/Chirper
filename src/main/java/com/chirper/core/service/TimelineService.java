package com.chirper.core.service;

import com.chirper.core.model.Post;
import com.chirper.core.model.TimeLine;
import com.chirper.core.model.Wall;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import static java.util.Collections.reverseOrder;
import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
@Slf4j
public class TimelineService {

    private final WallService wallService;
    private final FollowsService followsService;

    public TimeLine findForUser(String user) {
        log.info("Generating timeline for user {}", user);
        List<Post> posts = followsService.findForUser(user)
                .stream()
                .map(wallService::findByUser)
                .map(Wall::getPosts)
                .flatMap(Collection::stream)
                .sorted(reverseOrder())
                .collect(toList());
        return new TimeLine(posts, new Date());
    }
}
