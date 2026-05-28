package com.codekage.arcvaultx.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name="files")
@Data
public class FileMetaData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String originalFileName;
    private String storedFileName;
    private String filePath;
    private String fileType;
    private Long size;

    private Long folderId;

    @ManyToOne
    private User user;

    @ManyToOne
    private Folder folder;

    private LocalDateTime uploadAt;
}