package com.chirper.core.model;

import lombok.Value;

import java.util.List;

@Value
public class User {
    private final String name;
    private List<User> following;
}
