package com.chirper.core.controller;

import com.chirper.core.model.Post;
import com.chirper.core.service.PostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Post post(@Valid @RequestBody Post post) {
        log.info("User {} is trying to post a message of size {}", post.getUser(), post.getMessage().length());
        return postService.addPost(post);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Post can have maximum of 140 characters")
    @ExceptionHandler(ConstraintViolationException.class)
    public void error() {
    }
}
