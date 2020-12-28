package com.my.projmanager.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class MessageUpdateRequest extends MessageCreateRequest {
    private Long id;
}
