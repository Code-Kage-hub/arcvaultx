package com.codekage.arcvaultx.repository;

import com.codekage.arcvaultx.entity.FileMetaData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetaData, Long> {
}