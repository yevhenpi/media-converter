package ua.pidopryhora.mediaconverter.filemanager.entity;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FileDataRepository extends JpaRepository<FileData, Long> {
  FileData findByName(String fileName);

  boolean existsByName(String fileName);
}
