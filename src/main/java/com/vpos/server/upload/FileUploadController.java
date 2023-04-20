package com.vpos.server.upload;

import com.vpos.server.upload.storage.FileStorage;
import com.vpos.server.upload.storage.FileStorageRepository;
import com.vpos.server.upload.storage.FileUploadLocalStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

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
    private final FileStorageRepository fileStorageRepository;

    @Autowired
    Environment environment;

    @Autowired
    public FileUploadController(CloudinaryService cloudinaryService, FileUploadLocalStorageService fileUploadLocalStorageService, FileStorageRepository fileStorageRepository) {
        this.cloudinaryService = cloudinaryService;
        this.fileUploadLocalStorageService = fileUploadLocalStorageService;
        this.fileStorageRepository = fileStorageRepository;
    }

    @PostMapping
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads").toUriString());
        return ResponseEntity.created(uri).body(cloudinaryService.uploadFile(file));
    }

    @PostMapping(path = "/storage")
    public ResponseEntity<ArrayList<String>> uploadFilesLocalStorage(@RequestParam("files") MultipartFile[] files) {
        try {
            ArrayList<String> fileNames = new ArrayList<>();

            // Local address
//            InetAddress.getLocalHost().getHostAddress();
//            InetAddress.getLocalHost().getHostName();

            String URL = InetAddress.getLocalHost().getHostAddress() + ":" + environment.getProperty("local.server.port") + "/public/";

            Arrays.stream(files).forEach(file -> {
                fileUploadLocalStorageService.save(file);
                fileNames.add(file.getOriginalFilename());

               fileStorageRepository.save(new FileStorage(file.getOriginalFilename(), file.getContentType(), URL + file.getOriginalFilename(), new Date(), new Date() ));
            });

            URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/v1/uploads/storage").toUriString());
            return ResponseEntity.created(uri).body(fileNames);

        } catch (Exception ex) {
           throw new RuntimeException(ex);
        }

    }
}
