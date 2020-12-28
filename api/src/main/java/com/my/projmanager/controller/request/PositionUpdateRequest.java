package com.my.projmanager.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class PositionUpdateRequest extends PositionCreateRequest{
    private Long id;
}
