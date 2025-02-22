package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.AudioRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.jave2.FormatValidationService;
import ua.pidopryhora.mediaconverter.filemanager.service.rabbitmq.UpdateProducer;
import ua.pidopryhora.mediaconverter.filemanager.util.HashUtil;

import java.util.Map;
@Service
@AllArgsConstructor
public class ConversionRequestProcessor {
    //TODO:Add target format validation.

    private final UpdateProducer updateProducer;
    private final HashUtil hashUtil;
    private final FileDataService fileDataService;
    private final FormatValidationService formatValidationService;



    public ResponseEntity<?> process(AudioRequestDTO requestDTO){

        if(!formatValidationService.isFormatValid(requestDTO)){
            return ResponseEntity.badRequest().body(Map.of("error", "format is not supported"));
        }

        if (!fileDataService.isPresent(requestDTO.getFileName())) return ResponseEntity.badRequest().body(Map.of("error", "file is not found"));

        updateProducer.produce("CONVERSION_QUEUE", requestDTO);

        return ResponseEntity.ok().body(Map.of("message", "convertion is started",
                "hash", hashUtil.getHash(requestDTO)));
    }
}
