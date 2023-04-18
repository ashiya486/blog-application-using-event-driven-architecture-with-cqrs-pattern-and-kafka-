package com.BlogApplication.userService.userCommand.model;

import com.BlogApplication.userService.userCommand.entity.Role;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;
@Data
public class UserModel {
    private String userId;
    private String username;
    private String email;
    private String password;
    private Set<String> roles=new HashSet<>();

}
