package ua.pidopryhora.mediaconverter.filemanager.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.pidopryhora.mediaconverter.filemanager.entity.FileData;
import ua.pidopryhora.mediaconverter.filemanager.entity.FileDataRepository;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;


@RequiredArgsConstructor
@Service
public class FileDataService {

    private final FileDataRepository fileDataRepository;

    public FileData saveFile(UploadRequestDTO requestDTO){

        return fileDataRepository.save(

                FileData.builder()
                .name(requestDTO.getFileName())
                .size(requestDTO.getFileSize())
                .ownerId(requestDTO.getUserId())
                .build()
        );
    }

    public void deleteFile(String fileName, Long ownerId){

        fileDataRepository.delete(findByName(fileName, ownerId));
    }

    public boolean isPresent(String fileName, Long ownerId){
        return fileDataRepository.existsByNameAndOwnerId(fileName, ownerId);
    }

    public FileData findByName(String fileName, Long ownerId){
        return fileDataRepository.findByNameAndOwnerId(fileName, ownerId);
    }
}
