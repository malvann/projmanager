package com.my.projmanager.security.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponse {
    private String token;

//    use email
    private String username;
}
