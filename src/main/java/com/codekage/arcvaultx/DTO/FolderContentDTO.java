package com.codekage.arcvaultx.DTO;

import com.codekage.arcvaultx.entity.FileMetaData;
import com.codekage.arcvaultx.entity.Folder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FolderContentDTO {

    private Long id;
    private String name;
    private String type;

    public static FolderContentDTO fromFile(FileMetaData file) {
        FolderContentDTO dto = new FolderContentDTO();
        dto.setType("FILE");
        dto.setName(file.getOriginalFileName());
        dto.setId(file.getId());
        return dto;
    }

    public static FolderContentDTO fromFolder(Folder folder) {
        FolderContentDTO dto = new FolderContentDTO();
        dto.setType("FOLDER");
        dto.setId(folder.getFolderId());
        dto.setName(folder.getFolderName());
        return dto;
    }
}