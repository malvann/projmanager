package com.my.projmanager.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeCreateRequest extends EmployeeInviteCreateRequest {
    private String password;
    private String name;
    private String lastname;
    private String phone;
    private String skype;
    private String telegramm;
}
