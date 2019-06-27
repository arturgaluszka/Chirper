package com.chirper.core.controller;

import com.chirper.core.service.FollowsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/{user}/follows")
@RequiredArgsConstructor
@Slf4j
public class FollowController {

    private final FollowsService followsService;

    @RequestMapping(method = RequestMethod.POST)
    public String follow(@PathVariable String user, @RequestBody String toFollow) {
        log.info("User {} is trying to follow {}", user, toFollow);
        return followsService.addFollow(user, toFollow);
    }
}
