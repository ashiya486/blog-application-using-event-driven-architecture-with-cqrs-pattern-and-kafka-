package com.BlogApplication.userService.userCommand.repository;

import com.BlogApplication.userService.userCommand.entity.Role;
import com.BlogApplication.userService.userCommand.model.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    public Role findByName(ERole name);

}
