package com.codekage.arcvaultx.repository;

import com.codekage.arcvaultx.entity.FileMetadata;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileMetadata, Long> {
}