package com.BlogApplication.userService.userQuery.kafkaHandler;

import com.BlogApplication.userService.userQuery.entity.ERole;
import com.BlogApplication.userService.userQuery.entity.Role;
import com.BlogApplication.userService.userQuery.entity.User;
import com.BlogApplication.userService.userQuery.event.UserCreatedEvent;
import com.BlogApplication.userService.userQuery.repository.RoleRepository;
import com.BlogApplication.userService.userQuery.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class KafkaEventhandler {
    Logger logger= LoggerFactory.getLogger(KafkaEventhandler.class);
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @KafkaListener(topics = "UserEventsTopic",groupId = "user-query")
    public void handleKafaEvent(@Payload UserCreatedEvent userCreatedEvent){
        logger.error("inside event hanlder");
        User user=new User();
        Set<String> strRoles=userCreatedEvent.getRoles();
        Set<Role> roles=new HashSet<>();
        user.setUserId(userCreatedEvent.getUserId());
        user.setUsername(userCreatedEvent.getUsername());
        user.setEmail(userCreatedEvent.getEmail());
        user.setPassword(passwordEncoder.encode(userCreatedEvent.getPassword()));
        if(strRoles==null){
            Role userRole=roleRepository.findByName(ERole.ROLE_USER);
            roles.add(userRole);

        }else{
            strRoles.forEach(role ->{
                switch (role){
                    case "admin":
                        Role adminRole=roleRepository.findByName(ERole.ROLE_ADMIN);
                        roles.add(adminRole);
                        break;
                    default:
                        Role userRole=roleRepository.findByName(ERole.ROLE_USER);
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);


    }
}
