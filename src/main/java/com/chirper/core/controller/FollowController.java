package com.chirper.core.controller;

import com.chirper.core.service.FollowsService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{user}/follows")
@Api(tags = {"FOLLOWS"}, value = "Follow operations", description = "Operations on follows")
@RequiredArgsConstructor
@Slf4j
public class FollowController {

    private final FollowsService followsService;

    @ApiOperation(value = "Creates follow relation between users")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "followed user's name",reference = "ref")
    })
    @RequestMapping(method = RequestMethod.POST)
    public String follow(
            @ApiParam(value = "User creating the follow", required = true) @PathVariable String user,
            @ApiParam(value = "User who is being followed", required = true) @RequestBody String toFollow) {
        log.info("User {} is trying to follow {}", user, toFollow);
        return followsService.addFollow(user, toFollow);
    }
}
