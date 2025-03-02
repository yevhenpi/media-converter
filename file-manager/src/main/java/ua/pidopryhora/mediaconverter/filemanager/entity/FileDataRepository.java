package ua.pidopryhora.mediaconverter.filemanager.entity;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

  FileData findByNameAndOwnerId(String fileName, Long ownerId);
  @Query("SELECT f.name FROM FileData f WHERE f.ownerId = :ownerId")
  List<String> findFileNamesByOwnerId(@Param("ownerId") Long ownerId);


  boolean existsByNameAndOwnerId(String fileName, Long ownerId);
}
