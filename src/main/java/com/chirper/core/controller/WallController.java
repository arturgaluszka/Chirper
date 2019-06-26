package com.chirper.core.controller;

import com.chirper.core.model.Wall;
import com.chirper.core.service.WallService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/wall")
@RestController
@Slf4j
@RequiredArgsConstructor
public class WallController {

    private final WallService wallService;

    @RequestMapping("/{user}")
    public Wall wall(@PathVariable String user) {
        log.info("Retrieving wall for user {}", user);
        return wallService.findByUser(user);
    }
}
