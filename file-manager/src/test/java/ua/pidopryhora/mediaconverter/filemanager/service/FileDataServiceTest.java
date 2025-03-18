package ua.pidopryhora.mediaconverter.filemanager.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.pidopryhora.mediaconverter.filemanager.entity.FileData;
import ua.pidopryhora.mediaconverter.filemanager.entity.FileDataRepository;
import ua.pidopryhora.mediaconverter.filemanager.model.UploadRequestDTO;

@ExtendWith(MockitoExtension.class)
public class FileDataServiceTest {

    @Mock
    private FileDataRepository fileDataRepository;

    @InjectMocks
    private FileDataService fileDataService;

    @Test
    void shouldReturnSavedFileData() {
        // given
        UploadRequestDTO requestDTO = new UploadRequestDTO();
        requestDTO.setFileName("test.mp3");
        requestDTO.setFileSize(1000000L);
        requestDTO.setUserId(1L);

        // simulate that repository.save returns the passed-in entity
        when(fileDataRepository.save(any(FileData.class))).thenAnswer(invocation -> invocation.getArgument(0));

        // when
        FileData result = fileDataService.saveFile(requestDTO);

        // then
        assertThat(result.getName()).isEqualTo("test.mp3");
        assertThat(result.getSize()).isEqualTo(1000000L);
        assertThat(result.getOwnerId()).isEqualTo(1L);

        verify(fileDataRepository).save(any(FileData.class));
    }

    @Test
    void shouldCallDeleteWithFoundFile() {

        String fileName = "test.mp3";
        Long ownerId = 1L;
        FileData fileData = FileData.builder()
                .name(fileName)
                .ownerId(ownerId)
                .build();
        when(fileDataRepository.findByNameAndOwnerId(fileName, ownerId)).thenReturn(fileData);


        fileDataService.deleteFile(fileName, ownerId);


        verify(fileDataRepository).delete(fileData);
    }

    @Test
    void shouldReturnTrueWhenFileExists() {

        String fileName = "test.mp3";
        Long ownerId = 1L;
        when(fileDataRepository.existsByNameAndOwnerId(fileName, ownerId)).thenReturn(true);


        boolean result = fileDataService.isPresent(fileName, ownerId);


        assertThat(result).isTrue();
    }

    @Test
    void shouldReturnFileData() {

        String fileName = "test.mp3";
        Long ownerId = 1L;
        FileData fileData = FileData.builder()
                .name(fileName)
                .ownerId(ownerId)
                .build();
        when(fileDataRepository.findByNameAndOwnerId(fileName, ownerId)).thenReturn(fileData);


        FileData result = fileDataService.findByName(fileName, ownerId);


        assertThat(result).isEqualTo(fileData);
    }

    @Test
    void shouldReturnFileNames() {

        Long ownerId = 1L;
        List<String> fileNames = List.of("test1.mp3", "test2.mp3");
        when(fileDataRepository.findFileNamesByOwnerId(ownerId)).thenReturn(fileNames);


        List<String> result = fileDataService.getUserFiles(ownerId);


        assertThat(result).isEqualTo(fileNames);
    }
}
