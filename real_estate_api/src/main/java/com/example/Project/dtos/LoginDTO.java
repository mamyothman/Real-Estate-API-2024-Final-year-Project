package com.example.Project.dtos;

import com.example.Project.tables.Roles;
import lombok.Data;

@Data
public class LoginDTO {
    private String username;
    private String password;
    private Roles role_id;
    private String user_status;
}
