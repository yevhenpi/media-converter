package ua.pidopryhora.aws.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class S3Event {

    @JsonProperty("Records")
    private List<S3Record> records;

    public List<S3Record> getRecords() {
        return records;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class S3Record {
        @JsonProperty("eventName")
        private String eventName;

        @JsonProperty("s3")
        private S3Entity s3;

        public String getEventName() {
            return eventName;
        }

        public S3Entity getS3() {
            return s3;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class S3Entity {
        @JsonProperty("bucket")
        private Bucket bucket;

        @JsonProperty("object")
        private S3Object object;

        public Bucket getBucket() {
            return bucket;
        }

        public S3Object getObject() {
            return object;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Bucket {
        @JsonProperty("name")
        private String name;

        public String getName() {
            return name;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class S3Object {
        @JsonProperty("key")
        private String key;

        public String getKey() {
            return key;
        }
    }
}
