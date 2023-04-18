package com.BlogApplication.userService.userQuery.service;

import com.BlogApplication.userService.userQuery.entity.User;
import com.BlogApplication.userService.userQuery.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
//    @Trasactional ???
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        return com.BlogApplication.userService.userQuery.entity.UserDetails.build(user);
    }
}
