package ua.pidopryhora.mediaconverter.common.data;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Table(name = "JobData")
@Entity
@AllArgsConstructor
public class JobData {
    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private String jobId;

    @Column(name = "name", nullable = false)
    private String fileName;

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;

    @Column(name = "s3key")
    private String s3Key;

    @Column(name = "status", nullable = false)
    private String jobStatus;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    public JobData(){}
}
