package com.chirper.core.controller;

import com.chirper.core.model.Wall;
import com.chirper.core.service.WallService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/wall")
@RestController
@Api(tags = {"WALL"},value = "Wall operations", description = "Operations on users' walls")
@Slf4j
@RequiredArgsConstructor
public class WallController {

    private final WallService wallService;

    @ApiOperation(value = "Returns Wall for given user")
    @RequestMapping(value = "/{user}", method = RequestMethod.GET)
    public Wall wall(@ApiParam(value = "User who's wall You want to retrieve", required = true) @PathVariable String user) {
        log.info("Retrieving wall for user {}", user);
        return wallService.findByUser(user);
    }
}
