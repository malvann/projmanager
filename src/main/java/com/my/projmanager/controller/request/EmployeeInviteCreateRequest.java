package com.my.projmanager.controller.request;

import com.my.projmanager.model.impl.Position;
import com.my.projmanager.model.impl.Role;
import lombok.Data;

@Data
public class EmployeeInviteCreateRequest {
    private String mail;
    private Position position;
    private Role role;
}
