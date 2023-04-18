package com.BlogApplication.userService.userCommand.event;

import lombok.Data;

import java.util.UUID;
@Data
public class UserDeletedEvent {

    private UUID id;
    private String username;

}
