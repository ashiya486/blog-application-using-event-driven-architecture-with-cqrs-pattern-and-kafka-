package com.BlogApplication.userService.userCommand.Controller;

import com.BlogApplication.userService.userCommand.commands.CreateUserCommand;

//import com.BlogApplication.userService.userCommand.commands.DeleteUserCommand;
import com.BlogApplication.userService.userCommand.commands.DeleteUserCommand;
import com.BlogApplication.userService.userCommand.config.JwtUtill;
import com.BlogApplication.userService.userCommand.entity.User;
import com.BlogApplication.userService.userCommand.exception.BadRequestException;
import com.BlogApplication.userService.userCommand.model.UserModel;
import com.BlogApplication.userService.userCommand.payload.LoginRequest;
import com.BlogApplication.userService.userCommand.payload.RegisterRequest;
import com.BlogApplication.userService.userCommand.entity.Role;
import com.BlogApplication.userService.userCommand.model.ERole;
import com.BlogApplication.userService.userCommand.payload.response.AuthenticationResponse;
import com.BlogApplication.userService.userCommand.repository.RoleRepository;
import com.BlogApplication.userService.userCommand.repository.UserRepository;
import com.BlogApplication.userService.userCommand.service.UserDetailServiceImpl;

import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/user/command")
public class UserController {
    private CommandGateway commandGateway;
    @Autowired
    private RoleRepository repo;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailServiceImpl userDetailService;
    @Autowired
    private JwtUtill jwtUtill;
    @Autowired
            private UserRepository userRepository;



    public UserController(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String createUser(@RequestBody RegisterRequest registerRequest){
log.info("controller:create user");
        CreateUserCommand createUserCommand= CreateUserCommand.builder()
                .userId(UUID.randomUUID())
                .username(registerRequest.getUsername())
                .password(registerRequest.getPassword())
                .email(registerRequest.getEmail())
                .roles(registerRequest.getRoles())
                .build();
        return this.commandGateway.sendAndWait(createUserCommand);


    }
    @DeleteMapping("/delete/{username}")
    public ResponseEntity<?> deleteUser(@PathVariable("username") String username) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) auth.getPrincipal();
        String currentUsername = userDetails.getUsername();
        if (auth != null && (auth.getAuthorities().stream().anyMatch(a -> ((GrantedAuthority) a).getAuthority().equals("ROLE_ADMIN") || currentUsername.equals(username)))) {
            DeleteUserCommand deleteUserCommand=new DeleteUserCommand(UUID.randomUUID(),"username");
            return this.commandGateway.sendAndWait(deleteUserCommand);
        } else {
            return ResponseEntity.ok("user not deleted");//throw exception
        }
    }
//    @PutMapping
//    public void updateUser(UserModel userModel){
//
//    }
//        @PostMapping("/test")
//        public void testingreq(){
//log.info("line1");
//log.error("error1");
//
//        }
//    @PostMapping("/restore")
//    public Role create(){
//        Role role1=new Role(1,ERole.ROLE_USER);
//        Role role2=new Role(2,ERole.ROLE_ADMIN);
//repo.save(role1);
//repo.save(role2);
//       return role1 ;
//    }
}
