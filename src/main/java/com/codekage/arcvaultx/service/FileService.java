package com.codekage.arcvaultx.service;

import com.codekage.arcvaultx.entity.FileMetadata;
import com.codekage.arcvaultx.repository.FileMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileService {

    private final StorageService storageService;
    private final FileMetadataRepository repository;

    public void upload(MultipartFile file) throws IOException {

        String user = "testUser"; // later from JWT

        String path = storageService.upload(file, user);

        FileMetadata meta = new FileMetadata();
        meta.setFileName(file.getOriginalFilename());
        meta.setFilePath(path);
        meta.setFileType(file.getContentType());
        meta.setSize(file.getSize());
        meta.setOwner(user);
        meta.setCreatedAt(LocalDateTime.now());

        repository.save(meta);
    }
}