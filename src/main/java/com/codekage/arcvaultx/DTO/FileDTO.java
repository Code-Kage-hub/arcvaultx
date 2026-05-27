package com.codekage.arcvaultx.DTO;

import com.codekage.arcvaultx.entity.FileEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileDTO {
    private Long fileId;
    private String filename;
    private String fileType;
    private long fileSize;
    private LocalDateTime createdAt;

    public FileDTO(FileEntity metadata){
        this.fileId = metadata.getId();
        this.filename = metadata.getFileName();
        this.fileType = metadata.getFileType();
        this.fileSize = metadata.getSize();
        this.createdAt = metadata.getCreatedAt();
    }
}
