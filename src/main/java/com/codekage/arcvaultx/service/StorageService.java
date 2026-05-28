package com.codekage.arcvaultx.service;


import com.codekage.arcvaultx.entity.User;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String upload(MultipartFile file, User user) throws IOException;

    Resource download(String path) throws Exception;

    void delete(String path) throws IOException;
}
