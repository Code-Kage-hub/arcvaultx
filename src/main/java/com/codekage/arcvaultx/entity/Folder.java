package com.codekage.arcvaultx.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "Folders")
@Data
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long folderId;

    private String folderName;
    private String path;

    @ManyToOne
    private User user;

    @ManyToOne
    @JoinColumn(name="parent_folder_id")
    private Folder parentFolder;

    private LocalDateTime createdDt;
}
