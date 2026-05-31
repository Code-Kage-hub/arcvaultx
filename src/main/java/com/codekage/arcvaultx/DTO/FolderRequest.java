package com.codekage.arcvaultx.DTO;

import com.codekage.arcvaultx.entity.Folder;
import com.codekage.arcvaultx.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class FolderRequest {

    private Long folderId;
    private String folderName;
    private String path;

    private Long userId;           // ← just the ID, not full User entity
    private String userName;       // ← just what you need

    private Long parentFolderId;   // ← just the ID, not full Folder entity
    private String parentFolderName;

    private LocalDateTime createdDt;

    // constructor that maps Folder → FolderRequest
    public FolderRequest(Folder folder) {
        this.folderId          = folder.getFolderId();
        this.folderName        = folder.getFolderName();
        this.path              = folder.getPath();
        this.createdDt         = folder.getCreatedDt();

        // safely get user details
        if (folder.getUser() != null) {
            this.userId        = folder.getUser().getId();
            this.userName      = folder.getUser().getUserName();
        }

        // safely get parent folder details
        if (folder.getParentFolder() != null) {
            this.parentFolderId   = folder.getParentFolder().getFolderId();
            this.parentFolderName = folder.getParentFolder().getFolderName();
        }
    }
}
