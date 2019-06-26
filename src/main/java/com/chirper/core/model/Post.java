package com.chirper.core.model;

import lombok.Value;

import javax.validation.constraints.Size;
import java.util.Date;

@Value
public class Post implements Comparable<Post>{
    private String user;
    @Size(max = 140)
    private String message;
    private Date timestamp;

    @Override
    public int compareTo(Post other) {
        return timestamp.compareTo(other.getTimestamp());
    }
}
