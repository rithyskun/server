package com.vpos.server.upload.storage;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * @author Rithy SKUN
 * @created 19/04/2023 - 1:28 PM
 * @project server
 **/

public interface FileUploadLocalStorageService {
    void init();

    void save(MultipartFile files);

    Resource load(String fileName);

    void deleteAll();

    Stream<Path> loadAll();
}
