package com.BlogApplication.userService.userQuery.controller;

import com.BlogApplication.userService.userQuery.Query.GetUsersQuery;
import com.BlogApplication.userService.userQuery.config.JwtUtill;
import com.BlogApplication.userService.userQuery.exception.BadRequestException;
import com.BlogApplication.userService.userQuery.payload.AuthenticationResponse;
import com.BlogApplication.userService.userQuery.payload.LoginRequest;
import com.BlogApplication.userService.userQuery.payload.UserModel;
import com.BlogApplication.userService.userQuery.service.UserDetailsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequestMapping("/user/query")
public class UserController {
    public UserController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    private QueryGateway queryGateway;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private JwtUtill jwtUtill;

    @GetMapping
    public ResponseEntity<?> getUserBYUsername() {
        GetUsersQuery getUserQuery = new GetUsersQuery();
        log.info("load get user by username query ");
       List<UserModel> list=queryGateway.query(getUserQuery, ResponseTypes.multipleInstancesOf(UserModel.class)).join();
       return ResponseEntity.ok(list);
    }
    @PostMapping("/authenticate")
    public ResponseEntity<?>authenticateUser(@RequestBody LoginRequest loginRequest){
        try{
            Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        }
        catch(Exception e){throw new BadRequestException("invalid credentials");}
        final UserDetails userDetails=userDetailsService.loadUserByUsername(loginRequest.getUsername());
        final String jwt=jwtUtill.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
