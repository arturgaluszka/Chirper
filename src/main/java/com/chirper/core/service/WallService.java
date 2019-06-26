package com.chirper.core.service;

import com.chirper.core.model.Post;
import com.chirper.core.model.Wall;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class WallService {

    private final PostService postService;

    public Wall findByUser(String user) {
        List<Post> userPosts = postService.findForUser(user);
        return new Wall(userPosts, new Date());
    }
}
