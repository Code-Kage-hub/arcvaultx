package com.codekage.arcvaultx.repository;

import com.codekage.arcvaultx.DTO.FileDTO;
import com.codekage.arcvaultx.entity.Folder;
import com.codekage.arcvaultx.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FolderRepository extends JpaRepository<Folder, Long> {

    Page<Folder> findByUserAndParentFolder(User user, Folder parentFolder, Pageable pageable );

    Page<Folder> findByParentFolder(Folder parentFolder, Pageable pageable);
}
