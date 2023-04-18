package com.BlogApplication.userService.userCommand.commands;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.util.HashSet;
import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateUserCommand {
    @TargetAggregateIdentifier
    private String userId;
    private String username;


    private String email;
    private Set<String> roles=new HashSet<>();

    private String password;

}
