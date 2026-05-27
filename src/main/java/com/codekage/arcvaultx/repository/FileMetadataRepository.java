package com.codekage.arcvaultx.repository;

import com.codekage.arcvaultx.entity.FileEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileMetadataRepository extends JpaRepository<FileEntity, Long> {
}