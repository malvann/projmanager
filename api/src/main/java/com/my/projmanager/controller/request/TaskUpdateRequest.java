package com.my.projmanager.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class TaskUpdateRequest extends TaskCreateRequest{
    private Long id;
}
