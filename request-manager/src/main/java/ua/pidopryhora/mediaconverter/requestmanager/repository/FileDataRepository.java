package ua.pidopryhora.mediaconverter.requestmanager.repository;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ua.pidopryhora.mediaconverter.requestmanager.entity.FileData;

import java.util.List;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

  FileData findByNameAndOwnerId(String fileName, Long ownerId);

  @Query("SELECT f.name FROM FileData f WHERE f.ownerId = :ownerId")
  List<String> findFileNamesByOwnerId(@Param("ownerId") Long ownerId);

  boolean existsByNameAndOwnerId(String fileName, Long ownerId);
}
