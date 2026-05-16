package com.codekage.arcvaultx.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class FileMetadata {

    @Id
    @GeneratedValue
    private Long id;

    private String fileName;
    private String filePath;
    private String fileType;
    private Long size;

    private String owner;

    private LocalDateTime createdAt;
}