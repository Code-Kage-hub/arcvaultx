package com.codekage.arcvaultx.service;

import com.codekage.arcvaultx.DTO.FileDTO;
import com.codekage.arcvaultx.DTO.FolderContentDTO;
import com.codekage.arcvaultx.entity.FileMetaData;
import com.codekage.arcvaultx.entity.Folder;
import com.codekage.arcvaultx.entity.User;
import com.codekage.arcvaultx.repository.FileMetadataRepository;
import com.codekage.arcvaultx.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {

    private final StorageService storageService;
    private final FileMetadataRepository fileRepository;
    private final FolderRepository folderRepository;

    User user = new User(1L, "testUser"); // later from JWT

    public void upload(MultipartFile file, Long folderId) throws IOException {

        String path = storageService.upload(file, user);

        FileMetaData meta = new FileMetaData();
        meta.setOriginalFileName(file.getOriginalFilename());
        meta.setFilePath(path);
        meta.setFileType(file.getContentType());
        meta.setSize(file.getSize());
        meta.setUser(user);
        meta.setUploadAt(LocalDateTime.now());
        meta.setId(folderId);

        fileRepository.save(meta);
    }

    public Resource download(Long id) throws Exception{
        FileMetaData meta = fileRepository.findById(id).orElseThrow();
        Resource file = storageService.download(meta.getFilePath());
        return file;
    }

    public String delete(Long id) throws Exception{
        FileMetaData meta = fileRepository.findById(id).orElseThrow();
        storageService.delete(meta.getFilePath());
        fileRepository.delete(meta);
        return "Deleted";
    }

    public Page<FileDTO> getFilesWithPagination(int page, int size){
        Pageable pageable = PageRequest.of(page,size);
        Page<FileMetaData> fileMetadata =  fileRepository.findAll(pageable);
        Page<FileDTO> responses = fileMetadata.map(FileDTO::new);
        return responses;
    }

}