package com.codekage.arcvaultx.controller;

import com.codekage.arcvaultx.DTO.FileDTO;
import com.codekage.arcvaultx.DTO.FolderRequest;
import com.codekage.arcvaultx.entity.Folder;
import com.codekage.arcvaultx.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/files")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    /**
     * Upload a file and save metadata
     */
    @PostMapping("/upload")
    public ResponseEntity<String> upload(@RequestParam MultipartFile file, @RequestParam(defaultValue = "1") Folder folderId)
            throws IOException {

        fileService.upload(file, folderId);

        return ResponseEntity.ok("Uploaded Successfully");
    }

    /**
     * Download file using file id
     */
    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable Long id)
            throws Exception {

        Resource file = fileService.download(id);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + file.getFilename() + "\""
                )
                .body(file);
    }

    /**
     * Delete physical file and metadata
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id)
            throws Exception {

        fileService.delete(id);

        return ResponseEntity.ok("Deleted Successfully");
    }

    /**
     * Get paginated file list
     *
     * Example:
     * /api/files?page=0&size=5
     */
    @GetMapping
    public ResponseEntity<Page<FileDTO>> getFiles(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {

        Page<FileDTO> filePage =
                fileService.getFilesWithPagination(page, size);

        return ResponseEntity.ok(filePage);
    }

    @PostMapping("/folders")
    public ResponseEntity<String> uploadFolders(@RequestBody FolderRequest request)throws  Exception{
        fileService.uploadFolder(request.getFolderName(),request.getParentFolderId());
        return ResponseEntity.ok("Folder Created");
    }

    @GetMapping("/{parentFolderId}/contents")
    public ResponseEntity<Map<String, Object>> browseFolder(@PathVariable Long parentFolderId,
                                                                @RequestParam(defaultValue = "10") int size,
                                                                @RequestParam(defaultValue = "0") int pageNum)throws Exception{
        Map<String, Object> folderResponse = fileService.getFolderContents(parentFolderId, size, pageNum);
        return ResponseEntity.ok(folderResponse);
    }
}