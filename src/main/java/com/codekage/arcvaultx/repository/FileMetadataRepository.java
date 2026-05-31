package com.codekage.arcvaultx.repository;

import com.codekage.arcvaultx.DTO.FileDTO;
import com.codekage.arcvaultx.entity.FileMetaData;
import com.codekage.arcvaultx.entity.Folder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetaData, Long> {

    Page<FileMetaData> findByFolder(Folder folder, Pageable pageable);
}