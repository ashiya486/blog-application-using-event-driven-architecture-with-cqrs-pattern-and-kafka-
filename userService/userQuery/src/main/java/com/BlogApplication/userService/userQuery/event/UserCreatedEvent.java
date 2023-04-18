package com.BlogApplication.userService.userQuery.event;

import lombok.Data;

import java.util.Set;
@Data
public class UserCreatedEvent {
    private String userId;
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
    //private Set<String> role;
}
