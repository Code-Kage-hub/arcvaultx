package com.codekage.arcvaultx.service;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
public class LocalStorageService implements StorageService {

    private final String BASE_PATH = "C:/Users/ChaitanyaGandham/Downloads/Storage/";


    @Override
    public String upload(MultipartFile file, String user) throws IOException {

        String userDir = BASE_PATH + "users/" + user + "/";
        File dir = new File(userDir);

        if (!dir.exists()) dir.mkdirs();

        String fileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        String fullPath = userDir + fileName;

        file.transferTo(new File(fullPath));

        return fullPath;
    }

    @Override
    public Resource download(String path) throws Exception {
        Path file = Paths.get(path);
        Resource resource = new UrlResource(file.toUri());

        if (!resource.exists()) {
            throw new RuntimeException("File not found: " + path);
        }

        return resource;
    }

    @Override
    public void delete(String path) throws IOException {
        Files.deleteIfExists(Paths.get(path));
    }
}