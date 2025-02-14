package ua.pidopryhora.mediaconverter.filemanager.service;

import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

import java.util.concurrent.ConcurrentHashMap;
@Service
public class CashingService {
    //TODO: Change to REDIS

    private static final ConcurrentHashMap<String, UploadRequestDTO> cash = new ConcurrentHashMap<>();

    public void cashMetaData(UploadRequestDTO requestDTO){
        cash.put(requestDTO.getFileName(), requestDTO);

    }

    public boolean contains(UploadRequestDTO requestDTO){
        return cash.contains(requestDTO);

    }

    public void removeMetadata(String fileName){
        cash.remove(fileName);
    }

    public UploadRequestDTO getMetadata(String fileName){
       return cash.get(fileName);
    }
}
