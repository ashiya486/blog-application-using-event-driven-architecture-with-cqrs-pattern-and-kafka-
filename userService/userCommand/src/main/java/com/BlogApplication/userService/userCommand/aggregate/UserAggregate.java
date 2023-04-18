package com.BlogApplication.userService.userCommand.aggregate;

import com.BlogApplication.userService.userCommand.commands.CreateUserCommand;
//import com.BlogApplication.userService.userCommand.commands.DeleteUserCommand;
import com.BlogApplication.userService.userCommand.commands.DeleteUserCommand;
import com.BlogApplication.userService.userCommand.event.UserCreatedEvent;
import com.BlogApplication.userService.userCommand.event.UserDeletedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

import java.util.Set;
import java.util.UUID;

@Aggregate
public class UserAggregate {
    Logger logger= LoggerFactory.getLogger(UserAggregate.class);
    @AggregateIdentifier
    private UUID Id;
    private String username;
    private String email;
    private String password;
    private Set<String> roles;
    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand){
        UserCreatedEvent userCreatedEvent=new UserCreatedEvent();
        BeanUtils.copyProperties(createUserCommand,userCreatedEvent);
        logger.error("command handler");
        AggregateLifecycle.apply(userCreatedEvent);

    }
    @CommandHandler
    public UserAggregate(DeleteUserCommand deleteUserCommand){
        UserDeletedEvent userDeletedEvent=new UserDeletedEvent();
        BeanUtils.copyProperties(deleteUserCommand,userDeletedEvent);
        logger.error("command handler-delete");
        AggregateLifecycle.apply(userDeletedEvent);
    }
    @EventSourcingHandler
    public void on(UserDeletedEvent userDeletedEvent){
        this.Id =userDeletedEvent.getId();
        this.username=userDeletedEvent.getUsername();
    }
    public UserAggregate(){

    }
    @EventSourcingHandler
    public void on(UserCreatedEvent userCreatedEvent){
        this.Id =userCreatedEvent.getUserId();
        this.username=userCreatedEvent.getUsername();
        this.email=userCreatedEvent.getEmail();
        this.password=userCreatedEvent.getPassword();
        this.roles=userCreatedEvent.getRoles();
        logger.error("evenrt sourcing");
    }

}

