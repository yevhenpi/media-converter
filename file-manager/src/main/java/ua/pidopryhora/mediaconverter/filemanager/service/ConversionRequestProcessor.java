package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.AudioRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.rabbitmq.UpdateProducer;
import ua.pidopryhora.mediaconverter.filemanager.util.HashUtil;

import java.util.Map;
@Service
@AllArgsConstructor
public class ConversionRequestProcessor {
    //TODO:Add target format validation. Check if file present in database.

    private final UpdateProducer updateProducer;
    private final HashUtil hashUtil;



    public ResponseEntity<?> process(AudioRequestDTO requestDTO){

        updateProducer.produce("CONVERSION_QUEUE", requestDTO);

        return ResponseEntity.ok().body(Map.of("message", "convertion is started",
                "hash", hashUtil.getHash(requestDTO)));
    }
}
