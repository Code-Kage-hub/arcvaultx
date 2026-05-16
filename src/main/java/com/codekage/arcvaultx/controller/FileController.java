package com.codekage.arcvaultx.controller;

import com.codekage.arcvaultx.entity.FileMetadata;
import com.codekage.arcvaultx.repository.FileMetadataRepository;
import com.codekage.arcvaultx.service.FileService;
import com.codekage.arcvaultx.service.StorageService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FileMetadataRepository repository;
    private final StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<?> upload(@RequestParam MultipartFile file) throws IOException {
        fileService.upload(file);
        return ResponseEntity.ok("Uploaded");
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id) throws Exception {

        FileMetadata meta = repository.findById(id).orElseThrow();

        Resource file = storageService.download(meta.getFilePath());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + meta.getFileName() + "\"")
                .body(file);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) throws IOException {

        FileMetadata meta = repository.findById(id).orElseThrow();

        storageService.delete(meta.getFilePath());
        repository.delete(meta);

        return ResponseEntity.ok("Deleted");
    }
}
