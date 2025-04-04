package ua.pidopryhora.mediaconverter.requestmanager.service;

import org.junit.jupiter.api.Test;
import ua.pidopryhora.mediaconverter.common.model.AudioJobDTO;
import ua.pidopryhora.mediaconverter.requestmanager.model.AudioJobRequestDTO;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


public class AudioJobFactoryTest {

    private final AudioJobFactory factory = new AudioJobFactory();


    private static final String DEFAULT_FILENAME = "test.mp3";
    private static final String DEFAULT_OUTPUT_FORMAT = "mp3";
    private static final String DEFAULT_CODEC = "aac";
    private static final Long DEFAULT_USER_ID = 123L;

    private static final String EXPECTED_S3_KEY = "123/test.mp3";


    private AudioJobRequestDTO buildDefaultRequest() {
        AudioJobRequestDTO request = new AudioJobRequestDTO();
        request.setFileName(DEFAULT_FILENAME);
        request.setOutputFormat(DEFAULT_OUTPUT_FORMAT);
        request.setCodec(DEFAULT_CODEC);
        request.setUserId(DEFAULT_USER_ID);
        return request;
    }

    @Test
    void shouldCreateJobWithAllFields() {
        // given
        AudioJobRequestDTO requestDTO = buildDefaultRequest();
        requestDTO.setBitRate("128");
        requestDTO.setChannels("2");
        requestDTO.setSamplingRate("44100");
        requestDTO.setVolume("10");

        // when
        AudioJobDTO job = factory.createJob(requestDTO);

        // then
        assertThat(job.getFileName()).isEqualTo(DEFAULT_FILENAME);
        assertThat(job.getOutputFormat()).isEqualTo(DEFAULT_OUTPUT_FORMAT);
        assertThat(job.getCodec()).isEqualTo(DEFAULT_CODEC);
        assertThat(job.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(job.getS3Key()).isEqualTo(EXPECTED_S3_KEY);
        assertThat(job.getBitRate()).isEqualTo(128);
        assertThat(job.getChannels()).isEqualTo(2);
        assertThat(job.getSamplingRate()).isEqualTo(44100);
        assertThat(job.getVolume()).isEqualTo(10);
    }

    @Test
    void shouldCreateJobWithMissingOptionalFields() {
        // given
        AudioJobRequestDTO requestDTO = buildDefaultRequest();
        requestDTO.setBitRate(null);
        requestDTO.setChannels(null);
        requestDTO.setSamplingRate(null);
        requestDTO.setVolume(null);

        // when
        AudioJobDTO job = factory.createJob(requestDTO);

        // then
        assertThat(job.getFileName()).isEqualTo(DEFAULT_FILENAME);
        assertThat(job.getOutputFormat()).isEqualTo(DEFAULT_OUTPUT_FORMAT);
        assertThat(job.getCodec()).isEqualTo(DEFAULT_CODEC);
        assertThat(job.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(job.getS3Key()).isEqualTo(EXPECTED_S3_KEY);
        assertThat(job.getBitRate()).isEqualTo(0);
        assertThat(job.getChannels()).isEqualTo(0);
        assertThat(job.getSamplingRate()).isEqualTo(0);
        assertThat(job.getVolume()).isEqualTo(0);
    }
}