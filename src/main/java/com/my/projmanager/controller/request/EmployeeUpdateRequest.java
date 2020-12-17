package com.my.projmanager.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class EmployeeUpdateRequest extends EmployeeCreateRequest{
    private Long id;
    private Boolean fired;
}
