package com.my.projmanager.controller.request;

import com.my.projmanager.model.impl.Employee;
import lombok.Data;

@Data
public class MessageCreateRequest {
    private Employee sender;
    private String content;
}
