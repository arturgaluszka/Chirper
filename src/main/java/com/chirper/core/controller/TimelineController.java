package com.chirper.core.controller;

import com.chirper.core.model.TimeLine;
import com.chirper.core.service.TimelineService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user/{user}/timeline")
@RestController
@Api(tags = {"USER"}, description = "Operations on users' data")
@Slf4j
@RequiredArgsConstructor
public class TimelineController {

    private final TimelineService timelineService;

    @ApiOperation(value = "Returns Timeline for given user")
    @RequestMapping(method = RequestMethod.GET)
    public TimeLine wall(@ApiParam(value = "User who's timeline You want to retrieve", required = true) @PathVariable String user) {
        log.info("Retrieving timeline for user {}", user);
        return timelineService.findForUser(user);
    }
}

