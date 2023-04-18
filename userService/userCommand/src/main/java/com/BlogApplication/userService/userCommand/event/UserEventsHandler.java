package com.BlogApplication.userService.userCommand.event;

import com.BlogApplication.userService.userCommand.entity.Role;
import com.BlogApplication.userService.userCommand.entity.User;
import com.BlogApplication.userService.userCommand.model.ERole;
import com.BlogApplication.userService.userCommand.model.UserModel;
import com.BlogApplication.userService.userCommand.repository.RoleRepository;
import com.BlogApplication.userService.userCommand.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class UserEventsHandler {
    Logger logger= LoggerFactory.getLogger(UserEventsHandler.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    KafkaTemplate<String,Object> kafkaTemplate;
    @EventHandler
    public void on(UserCreatedEvent userCreatedEvent){
//        Logger logger=LoggerFactory.getLogger(UserEventsHandler.class);
        log.error("event handler create");
//        this.kafkaTemplate.send("UserEventsTopic",userCreatedEvent);
    }
//    @EventHandler
//    public void on(UserDeletedEvent userDeletedEvent){
//        logger.error("event hanler-delete");
//        this.kafkaTemplate.send("UserEventsTopic",userDeletedEvent);
//    }
}
