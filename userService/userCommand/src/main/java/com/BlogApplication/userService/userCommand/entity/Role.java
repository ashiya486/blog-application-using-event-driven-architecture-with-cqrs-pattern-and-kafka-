package com.BlogApplication.userService.userCommand.entity;

import com.BlogApplication.userService.userCommand.model.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

@Enumerated(EnumType.STRING)
    private ERole name;
}
