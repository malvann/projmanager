package com.my.projmanager.security.model;

import lombok.Data;

@Data
public class AuthRequest {

//    use email
    private String login;
    private String password;
}
