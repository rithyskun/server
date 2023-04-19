package com.vpos.server.upload;

import com.vpos.server.upload.storage.FileUploadLocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Rithy SKUN
 * @created 19/04/2023 - 9:46 AM
 * @project server
 **/

@CrossOrigin
@RestController
@RequestMapping(path = "api/v1/uploads")
public class FileUploadController {

    private final CloudinaryService cloudinaryService;
    private final FileUploadLocalStorageService fileUploadLocalStorageService;

    @Autowired
    public FileUploadController(CloudinaryService cloudinaryService, FileUploadLocalStorageService fileUploadLocalStorageService) {
        this.cloudinaryService = cloudinaryService;
        this.fileUploadLocalStorageService = fileUploadLocalStorageService;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads").toUriString());
        return ResponseEntity.created(uri).body(cloudinaryService.uploadFile(file));
    }

    @PostMapping(path = "/storage")
    public ResponseEntity uploadFilesLocalStorage(@RequestParam("files") MultipartFile[] files) {
        String message = "";
        try {
            List<String> fileNames = new ArrayList<>();

            Arrays.stream(files).forEach(file -> {
                fileUploadLocalStorageService.save(file);
                fileNames.add(file.getOriginalFilename());
            });

            System.out.println(fileNames);

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads/storage").toUriString());
            return ResponseEntity.created(uri).build();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

    }
}
