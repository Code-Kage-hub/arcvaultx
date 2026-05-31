package com.codekage.arcvaultx.service;

import com.codekage.arcvaultx.DTO.FolderContentDTO;
import com.codekage.arcvaultx.entity.FileMetaData;
import com.codekage.arcvaultx.entity.Folder;
import com.codekage.arcvaultx.entity.User;
import com.codekage.arcvaultx.repository.FileMetadataRepository;
import com.codekage.arcvaultx.repository.FolderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class FolderService {

    private final FileMetadataRepository fileRepository;
    private final FolderRepository folderRepository;

    User user = new User(1L, "testUser"); // later from JWT

    public void uploadFolder(String name, Long parentFolderId)throws  Exception{
        Folder folder = new Folder();
        if(parentFolderId != null) {
            Folder parentFolder = folderRepository.findById(parentFolderId).orElseThrow(() -> new RuntimeException("Parent Folder Not Found"));
            folder.setParentFolder(parentFolder);
        }
        folder.setFolderName(name);
        folder.setUser(user);
        folderRepository.save(folder);
    }

    public Map<String, Object> getFolderContents(Long parentFolderId, int size, int page){

        Folder currentFolder = folderRepository.findById(parentFolderId).orElseThrow(() -> new RuntimeException("Folder not found"));

        Pageable pageable = PageRequest.of(page, size);

        // fetch files
        Page<FileMetaData> filePage = fileRepository
                .findByFolder(currentFolder, pageable);

        // fetch folders
        Page<Folder> folderPage = folderRepository
                .findByParentFolder(currentFolder, pageable);

        // convert to combined DTO
        List<FolderContentDTO> files = filePage.getContent()
                .stream()
                .map(FolderContentDTO::fromFile)
                .toList();

        List<FolderContentDTO> folders = folderPage.getContent()
                .stream()
                .map(FolderContentDTO::fromFolder)
                .toList();

        // merge both lists
        List<FolderContentDTO> combined = new ArrayList<>();
        combined.addAll(folders);   // folders first
        combined.addAll(files);     // then files

        // build response
        Map<String, Object> response = new HashMap<>();
        response.put("contents", combined);
        response.put("totalFiles", filePage.getTotalElements());
        response.put("totalFolders", folderPage.getTotalElements());
        response.put("currentPage", page);
        response.put("pageSize", size);

        return response;

    }

}
