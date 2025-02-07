package ua.pidopryhora.mediaconverter.filemanager.model;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserDataDTO {

    private String UserId;
    private String UserRole;
}
