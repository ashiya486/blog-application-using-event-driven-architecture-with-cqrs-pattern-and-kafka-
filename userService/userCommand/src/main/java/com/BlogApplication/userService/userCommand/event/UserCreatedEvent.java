package com.BlogApplication.userService.userCommand.event;

import lombok.Data;

import java.util.Set;
import java.util.UUID;

@Data
public class UserCreatedEvent {
    private UUID userId;
    private String username;
    private String email;
    private Set<String> roles;
    private String password;
    //private Set<String> role;
}
