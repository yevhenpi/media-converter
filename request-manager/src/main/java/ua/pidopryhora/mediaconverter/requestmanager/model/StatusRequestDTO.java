package ua.pidopryhora.mediaconverter.requestmanager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StatusRequestDTO extends RequestDTO {
    private List<String> jobIds;
}
