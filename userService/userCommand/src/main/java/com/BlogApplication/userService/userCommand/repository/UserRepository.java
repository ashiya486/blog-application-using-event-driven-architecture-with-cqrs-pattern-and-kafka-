package com.BlogApplication.userService.userCommand.repository;

import com.BlogApplication.userService.userCommand.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String Username);
}

