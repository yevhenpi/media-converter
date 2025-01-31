package ua.pidopryhora.mediaconverter.filemanager.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;

import java.util.List;
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class S3Event {

    @JsonProperty("Records")
    private List<S3Record> records;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class S3Record {

        @JsonProperty("eventName")
        private String eventName;

        @JsonProperty("s3")
        private S3Entity s3;

    }
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class S3Entity {

        @JsonProperty("bucket")
        private Bucket bucket;

        @JsonProperty("object")
        private S3Object object;

    }
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Bucket {

        @JsonProperty("name")
        private String name;

    }
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class S3Object {

        @JsonProperty("key")
        private String key;

        @JsonProperty("size")
        private Integer size;

    }
}
