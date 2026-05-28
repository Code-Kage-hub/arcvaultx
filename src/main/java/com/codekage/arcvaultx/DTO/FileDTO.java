package com.codekage.arcvaultx.DTO;

import com.codekage.arcvaultx.entity.FileMetaData;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FileDTO {
    private Long fileId;
    private String filename;
    private String fileType;
    private long fileSize;
    private LocalDateTime createdAt;

    public FileDTO(FileMetaData metadata){
        this.fileId = metadata.getId();
        this.filename = metadata.getOriginalFileName();
        this.fileType = metadata.getFileType();
        this.fileSize = metadata.getSize();
        this.createdAt = metadata.getUploadAt();
    }
}
