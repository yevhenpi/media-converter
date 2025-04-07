package ua.pidopryhora.mediaconverter.requestmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ua.pidopryhora.mediaconverter.common.model.AuthenticatedRequestDTO;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusRequestDTO extends AuthenticatedRequestDTO {
    private List<String> jobIds;
}
