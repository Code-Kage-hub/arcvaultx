package com.codekage.arcvaultx.service;

import com.codekage.arcvaultx.DTO.FileDTO;
import com.codekage.arcvaultx.entity.FileEntity;
import com.codekage.arcvaultx.repository.FileMetadataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FileService {

    private final StorageService storageService;
    private final FileMetadataRepository repository;

    String user = "testUser"; // later from JWT

    public void upload(MultipartFile file) throws IOException {

        String path = storageService.upload(file, user);

        FileEntity meta = new FileEntity();
        meta.setFileName(file.getOriginalFilename());
        meta.setFilePath(path);
        meta.setFileType(file.getContentType());
        meta.setSize(file.getSize());
        meta.setOwner(user);
        meta.setCreatedAt(LocalDateTime.now());

        repository.save(meta);
    }

    public Resource download(Long id) throws Exception{
        FileEntity meta = repository.findById(id).orElseThrow();
        Resource file = storageService.download(meta.getFilePath());
        return file;
    }

    public String delete(Long id) throws Exception{
        FileEntity meta = repository.findById(id).orElseThrow();
        storageService.delete(meta.getFilePath());
        repository.delete(meta);
        return "Deleted";
    }

    public Page<FileDTO> getFilesWithPagination(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<FileEntity> fileMetadata =  repository.findAll(pageable);
        Page<FileDTO> responses = fileMetadata.map(FileDTO::new);
        return responses;
     }

}