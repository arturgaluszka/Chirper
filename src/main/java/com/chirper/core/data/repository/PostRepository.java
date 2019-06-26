package com.chirper.core.data.repository;

import com.chirper.core.model.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class PostRepository {
    private Map<String, List<Post>> posts;

    public PostRepository() {
        log.info("initializing post repository");
        posts = new HashMap<>();
    }

    public List<Post> findByUser(String user) {
        List<Post> posts = this.posts.get(user);
        if (posts == null) return new ArrayList<>();
        log.info("Found {} posts for user {}", posts.size(), user);
        return posts;
    }

    public Post save(Post post) {
        posts.putIfAbsent(post.getUser(), new ArrayList<>());
        posts.get(post.getUser()).add(post);
        log.info("Saving post from {} with size {}", post.getUser(), post.getMessage().length());
        return post;
    }
}
