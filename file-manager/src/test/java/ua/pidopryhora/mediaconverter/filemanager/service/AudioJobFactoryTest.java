package ua.pidopryhora.mediaconverter.filemanager.service;

import org.junit.jupiter.api.Test;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.filemanager.model.AudioConversionRequestDTO;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AudioJobFactoryTest {

    private final AudioJobFactory factory = new AudioJobFactory();


    private static final String DEFAULT_FILENAME = "test.mp3";
    private static final String DEFAULT_OUTPUT_FORMAT = "mp3";
    private static final String DEFAULT_CODEC = "aac";
    private static final Long DEFAULT_USER_ID = 123L;
    private static final String DEFAULT_JOB_ID = "job123";
    private static final String EXPECTED_S3_KEY = "123/test.mp3";


    private AudioConversionRequestDTO buildDefaultRequest() {
        AudioConversionRequestDTO request = new AudioConversionRequestDTO();
        request.setFileName(DEFAULT_FILENAME);
        request.setOutputFormat(DEFAULT_OUTPUT_FORMAT);
        request.setCodec(DEFAULT_CODEC);
        request.setUserId(DEFAULT_USER_ID);
        return request;
    }

    @Test
    void shouldCreateJobWithAllFields() {
        // given
        AudioConversionRequestDTO requestDTO = buildDefaultRequest();
        requestDTO.setBitRate("128");
        requestDTO.setChannels("2");
        requestDTO.setSamplingRate("44100");
        requestDTO.setVolume("10");

        // when
        AudioJobDTO job = factory.createJob(requestDTO, DEFAULT_JOB_ID);

        // then
        assertThat(job.getFileName()).isEqualTo(DEFAULT_FILENAME);
        assertThat(job.getOutputFormat()).isEqualTo(DEFAULT_OUTPUT_FORMAT);
        assertThat(job.getCodec()).isEqualTo(DEFAULT_CODEC);
        assertThat(job.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(job.getS3Key()).isEqualTo(EXPECTED_S3_KEY);
        assertThat(job.getJobId()).isEqualTo(DEFAULT_JOB_ID);
        assertThat(job.getBitRate()).isEqualTo(128);
        assertThat(job.getChannels()).isEqualTo(2);
        assertThat(job.getSamplingRate()).isEqualTo(44100);
        assertThat(job.getVolume()).isEqualTo(10);
    }

    @Test
    void shouldCreateJobWithMissingOptionalFields() {
        // given
        AudioConversionRequestDTO requestDTO = buildDefaultRequest();
        requestDTO.setBitRate(null);
        requestDTO.setChannels(null);
        requestDTO.setSamplingRate(null);
        requestDTO.setVolume(null);

        // when
        AudioJobDTO job = factory.createJob(requestDTO, DEFAULT_JOB_ID);

        // then
        assertThat(job.getFileName()).isEqualTo(DEFAULT_FILENAME);
        assertThat(job.getOutputFormat()).isEqualTo(DEFAULT_OUTPUT_FORMAT);
        assertThat(job.getCodec()).isEqualTo(DEFAULT_CODEC);
        assertThat(job.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(job.getS3Key()).isEqualTo(EXPECTED_S3_KEY);
        assertThat(job.getJobId()).isEqualTo(DEFAULT_JOB_ID);
        assertThat(job.getBitRate()).isNull();
        assertThat(job.getChannels()).isNull();
        assertThat(job.getSamplingRate()).isNull();
        assertThat(job.getVolume()).isNull();
    }
}