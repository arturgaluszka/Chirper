package com.chirper.core.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Value;

import javax.validation.constraints.Size;
import java.util.Date;

@Value
@ApiModel
public class Post implements Comparable<Post>{
    @ApiModelProperty(required = true)
    private String user;
    @Size(max = 140)
    @ApiModelProperty(required = true)
    private String message;
    private Date timestamp;

    @Override
    public int compareTo(Post other) {
        return timestamp.compareTo(other.getTimestamp());
    }
}
