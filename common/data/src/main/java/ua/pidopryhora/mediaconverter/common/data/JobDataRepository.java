package ua.pidopryhora.mediaconverter.common.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface JobDataRepository extends JpaRepository<JobData, String> {
    JobData getByJobId(String jobId);
    List<JobData> findByOwnerId(Long ownerId);
    List<JobData> findByCreatedAtBefore(LocalDateTime threshold);
    @Query("SELECT j.s3Key FROM JobData j WHERE j.jobId = :jobId")
    String findS3KeyByJobId(@Param("jobId") String jobId);

    @Modifying
    @Transactional
    @Query("UPDATE JobData j SET j.jobStatus = :status WHERE j.jobId = :jobId")
    int updateJobStatus(@Param("jobId") String jobId, @Param("status") String status);

    @Modifying
    @Transactional
    @Query("UPDATE JobData j SET j.s3Key = :s3Key WHERE j.jobId = :jobId")
    int updateS3Key(@Param("jobId") String jobId, @Param("s3Key") String key);


}
