package com.codekage.arcvaultx.controller;

import com.codekage.arcvaultx.DTO.FolderRequest;
import com.codekage.arcvaultx.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/folders")
@RequiredArgsConstructor
public class FolderController {
    private final FolderService folderService;

    @PostMapping
    public ResponseEntity<String> uploadFolders(@RequestBody FolderRequest request)throws  Exception{
        folderService.uploadFolder(request.getFolderName(),request.getParentFolderId());
        return ResponseEntity.ok("Folder Created");
    }

    @GetMapping("/{parentFolderId}/contents")
    public ResponseEntity<Map<String, Object>> browseFolder(@PathVariable Long parentFolderId,
                                                            @RequestParam(defaultValue = "10") int size,
                                                            @RequestParam(defaultValue = "0") int pageNum)throws Exception{
        Map<String, Object> folderResponse = folderService.getFolderContents(parentFolderId, size, pageNum);
        return ResponseEntity.ok(folderResponse);
    }
}
