package com.BlogApplication.userService.userQuery.repository;

import com.BlogApplication.userService.userQuery.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String username);
}
