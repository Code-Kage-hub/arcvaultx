package com.codekage.arcvaultx.DTO;

import com.codekage.arcvaultx.entity.Folder;
import lombok.Data;

@Data
public class FolderRequest {
    private String folderName;
    private Folder folderId;
}
