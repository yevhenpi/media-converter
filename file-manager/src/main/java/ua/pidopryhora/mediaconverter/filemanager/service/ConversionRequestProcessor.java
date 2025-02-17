package ua.pidopryhora.mediaconverter.filemanager.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.common.model.ConversionRequestDTO;
import ua.pidopryhora.mediaconverter.filemanager.service.rabbitmq.UpdateProducer;

import java.util.Map;
@Service
public class ConversionRequestProcessor {
    //TODO:Add target format validation. Check if file present in database.

    private final UpdateProducer updateProducer;

    public ConversionRequestProcessor(UpdateProducer updateProducer) {
        this.updateProducer = updateProducer;
    }


    public ResponseEntity<?> process(ConversionRequestDTO requestDTO){

        updateProducer.produce("CONVERSION_QUEUE", requestDTO);

        return ResponseEntity.ok().body(Map.of("message", "convertion is started",
                "requestID", requestDTO.getRequestId()));
    }
}
