package ua.pidopryhora.mediaconverter.requestmanager.repository;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import ua.pidopryhora.mediaconverter.requestmanager.entity.FileData;
import ua.pidopryhora.mediaconverter.requestmanager.service.EventProcessor;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class FileDataRepositoryTest {

    @Autowired
    private FileDataRepository testRepository;



    @AfterEach
    void tearDown(){
        testRepository.deleteAll();
    }

    @Test
    void shouldFindByNameAndOwnerId() {
        //given
        long testSize = 1000000L;
        FileData fileData = FileData.builder()
                .name("test.mp3")
                .size(testSize)
                .ownerId(1L)
                .build();
        testRepository.save(fileData);

        //when
        FileData expected = testRepository.findByNameAndOwnerId("test.mp3",1L);
        //than
        assertThat(expected).isEqualTo(fileData);


    }

    @Test
    void shouldFindFileNamesByOwnerId() {
        List<String> testNames = new ArrayList<>();
        String testName = "test.mp3";
        testNames.add(testName);
        FileData fileData = FileData.builder()
                .name(testName)
                .size(1000000L)
                .ownerId(1L)
                .build();
        testRepository.save(fileData);

        //when
        List<String> expected = testRepository.findFileNamesByOwnerId(1L);
        //than
        assertThat(expected).isEqualTo(testNames);
    }

    @Test
    void shouldExistsByNameAndOwnerId() {
        //given
        FileData fileData = FileData.builder()
                .name("test.mp3")
                .size(1000000L)
                .ownerId(1L)
                .build();
        testRepository.save(fileData);

        //when
        boolean expected = testRepository.existsByNameAndOwnerId("test.mp3",1L);
        //than
        assertThat(expected).isTrue();
    }

    @TestConfiguration
    static class MocksConfig {
        @Bean
        public RabbitTemplate rabbitTemplate() {
            return mock(RabbitTemplate.class);
        }

        @Bean
        public EventProcessor eventProcessor() {
            return mock(EventProcessor.class);
        }



    }
}