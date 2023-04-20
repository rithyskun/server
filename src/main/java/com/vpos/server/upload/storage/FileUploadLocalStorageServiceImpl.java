package com.vpos.server.upload.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * @author Rithy SKUN
 * @created 19/04/2023 - 1:37 PM
 * @project server
 **/

@Service
public class FileUploadLocalStorageServiceImpl implements FileUploadLocalStorageService{

    private final Path root = Paths.get("src/main/resources/public");

    @Override
    public void init() {
        try {
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }

        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void save(MultipartFile file) {
        try {
            Files.copy(file.getInputStream(),
                    this.root.resolve(Objects.requireNonNull(file.getOriginalFilename())),
                    StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException ex) {
           throw new RuntimeException("Could not save image file: " + ex);
        }
    }

    @Override
    public Resource load(String fileName) {
        return null;
    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Stream<Path> loadAll() {
        return null;
    }
}
