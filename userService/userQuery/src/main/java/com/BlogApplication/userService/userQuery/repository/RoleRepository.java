package com.BlogApplication.userService.userQuery.repository;

import com.BlogApplication.userService.userQuery.entity.ERole;
import com.BlogApplication.userService.userQuery.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(ERole name);
}
