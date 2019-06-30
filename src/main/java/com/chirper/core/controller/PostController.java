package com.chirper.core.controller;

import com.chirper.core.model.Post;
import com.chirper.core.service.PostService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

@RestController
@RequestMapping("/post")
@Api(tags = {"POST"}, value = "Post operations", description = "Operations on posts")
@RequiredArgsConstructor
@Slf4j
public class PostController {

    private final PostService postService;

    @ApiOperation(value = "Creates post for given user with requested message")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Post created", response = Post.class),
            @ApiResponse(code = 400, message = "Validation failed")})
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
