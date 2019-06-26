package com.chirper.core.service;

import com.chirper.core.data.repository.PostRepository;
import com.chirper.core.model.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> findForUser(String user) {
        return postRepository.findByUser(user);
    }

    public Post addPost(Post post) {
        Post toPost = new Post(post.getUser(), post.getMessage(), new Date());
        return postRepository.save(toPost);
    }
}
