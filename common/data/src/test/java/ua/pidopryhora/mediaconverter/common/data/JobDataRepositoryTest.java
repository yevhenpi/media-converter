package ua.pidopryhora.mediaconverter.common.data;


import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ContextConfiguration(classes = TestApplication.class)
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
class JobDataRepositoryTest {
    private static final String JOB_ID = "jobId";
    private static final String FILE_NAME = "filename";
    private static final Long OWNER_ID = 1L;
    private static final String S3_KEY = "s3Key";
    private static final String JOB_STATUS = "STATUS";

    @Autowired
    private JobDataRepository testRepository;
    @Autowired
    private EntityManager entityManager;

    @AfterEach
    void tearDown(){
        testRepository.deleteAll();
    }

    private JobData buildJobData(String jobId, String fileName, Long ownerId, String s3Key, String jobStatus) {
        return JobData.builder()
                .jobId(jobId)
                .fileName(fileName)
                .ownerId(ownerId)
                .s3Key(s3Key)
                .jobStatus(jobStatus)
                .build();
    }

    private JobData defaultJob() {
        return buildJobData(JOB_ID, FILE_NAME, OWNER_ID, S3_KEY, JOB_STATUS);
    }



    @Test
    void shouldGetByJobId() {
        // given
        JobData savedJob = testRepository.save(defaultJob());

        // when
        JobData result = testRepository.getByJobId(JOB_ID);

        // then
        assertThat(result).isEqualTo(savedJob);
    }

    @Test
    void shouldFindByOwnerId() {
        // given
        JobData savedJob = testRepository.save(defaultJob());
        List<JobData> expected = List.of(savedJob);

        // when
        List<JobData> result = testRepository.findByOwnerId(OWNER_ID);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldFindByCreatedAtBefore() {
        // given
        JobData savedJob = testRepository.save(defaultJob());
        List<JobData> expected = List.of(savedJob);
        LocalDateTime threshold = LocalDateTime.now().plusDays(1);

        // when
        List<JobData> result = testRepository.findByCreatedAtBefore(threshold);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @Test
    void shouldFindS3KeyByJobId() {
        // given
        testRepository.save(defaultJob());

        // when
        String result = testRepository.findS3KeyByJobId(JOB_ID);

        // then
        assertThat(result).isEqualTo(S3_KEY);
    }

    @Test
    void existsByJobId() {
        // given
        testRepository.save(defaultJob());

        // when
        boolean result = testRepository.existsByJobId(JOB_ID);

        // then
        assertThat(result).isTrue();
    }

    @Test
    void shouldUpdateJobStatus() {
        // given
        testRepository.save(defaultJob());

        // when
        int updatedRows = testRepository.updateJobStatus(JOB_ID, "NEW_STATUS");
        entityManager.clear(); // Clear persistence context to avoid stale data

        // then
        assertThat(updatedRows).isEqualTo(1);
        JobData updatedJob = testRepository.getByJobId(JOB_ID);
        assertThat(updatedJob.getJobStatus()).isEqualTo("NEW_STATUS");
    }

    @Test
    void shouldUpdateS3Key() {
        // given
        testRepository.save(buildJobData(JOB_ID, FILE_NAME, OWNER_ID, "oldKey", JOB_STATUS));

        // when
        int updatedRows = testRepository.updateS3Key(JOB_ID, "newKey");
        entityManager.clear(); // Clear persistence context after update

        // then
        assertThat(updatedRows).isEqualTo(1);
        JobData updatedJob = testRepository.getByJobId(JOB_ID);
        assertThat(updatedJob.getS3Key()).isEqualTo("newKey");
    }
}