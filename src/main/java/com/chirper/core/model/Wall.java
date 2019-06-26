package com.chirper.core.model;

import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
public class Wall {
    private List<Post> posts;
    private Date timestamp;
}
