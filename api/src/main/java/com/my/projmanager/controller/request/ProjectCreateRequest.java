package com.my.projmanager.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.my.projmanager.model.impl.Customer;
import com.my.projmanager.model.impl.Employee;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class ProjectCreateRequest {
    private String name;
    private Customer customer;
    private Employee director;
    private String descr;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Timestamp termonationPlanDate;
}
