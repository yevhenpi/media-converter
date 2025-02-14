package ua.pidopryhora.mediaconverter.filemanager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Builder
@Table(name = "FileData")
public class FileData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", updatable = false, nullable = false)
    private Long fileId;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @Column(name = "size", nullable = false)
    private Long size;

    @Column(name = "ownerId", nullable = false)
    private Long ownerId;


}
