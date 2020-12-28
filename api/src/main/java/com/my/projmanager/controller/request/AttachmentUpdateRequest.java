package com.my.projmanager.controller.request;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class AttachmentUpdateRequest extends AttachmentCreateRequest{
    private Long id;
}
