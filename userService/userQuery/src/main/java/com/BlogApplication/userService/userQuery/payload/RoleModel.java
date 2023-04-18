package com.BlogApplication.userService.userQuery.payload;

import com.BlogApplication.userService.userQuery.entity.ERole;
import lombok.Data;

@Data
public class RoleModel {
    private long id;
    private ERole name;
}
