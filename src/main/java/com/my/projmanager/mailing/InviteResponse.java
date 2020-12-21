package com.my.projmanager.mailing;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InviteResponse {
    private String inviteToken;
}
