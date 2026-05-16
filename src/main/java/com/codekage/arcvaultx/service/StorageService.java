package com.codekage.arcvaultx.service;


import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface StorageService {
    String upload(MultipartFile file, String user) throws IOException;

    Resource download(String path) throws Exception;

    void delete(String path) throws IOException;
}
