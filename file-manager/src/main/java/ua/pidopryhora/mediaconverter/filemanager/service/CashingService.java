package ua.pidopryhora.mediaconverter.filemanager.service;

import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.RequestMetadataDTO;

import java.util.concurrent.ConcurrentHashMap;
@Service
public class CashingService {
    //TODO: Change to REDIS

    private static final ConcurrentHashMap<String, RequestMetadataDTO> cash = new ConcurrentHashMap<>();

    public void cashMetaData(RequestMetadataDTO requestMetadataDTO){
        cash.put(requestMetadataDTO.getFileName(), requestMetadataDTO);

    }

    public boolean contains(RequestMetadataDTO requestMetadataDTO){
        return cash.contains(requestMetadataDTO);

    }

    public void removeMetadata(String fileName){
        cash.remove(fileName);
    }

    public RequestMetadataDTO getMetadata(String fileName){
       return cash.get(fileName);
    }
}
