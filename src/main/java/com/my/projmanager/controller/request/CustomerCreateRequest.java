package com.my.projmanager.controller.request;

import lombok.Data;

@Data
public class CustomerCreateRequest {
    private String name;
    private String lastname;
    private String phone;
    private String skype;
    private String telegramm;
    private String address;
    private String mail;
}
