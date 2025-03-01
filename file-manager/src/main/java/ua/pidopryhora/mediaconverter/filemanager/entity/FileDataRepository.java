package ua.pidopryhora.mediaconverter.filemanager.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {

  FileData findByNameAndOwnerId(String fileName, Long ownerId);

  boolean existsByName(String fileName);

  boolean existsByNameAndOwnerId(String fileName, Long ownerId);
}
