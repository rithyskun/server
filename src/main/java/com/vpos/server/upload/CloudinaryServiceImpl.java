package com.vpos.server.upload;

import com.cloudinary.utils.ObjectUtils;
import com.vpos.server.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

/**
 * @author Rithy SKUN
 * @created 19/04/2023 - 9:59 AM
 * @project server
 **/

@Service
public class CloudinaryServiceImpl implements CloudinaryService {

    private final CloudinaryConfiguration cloudinaryConfiguration;

    @Autowired
    public CloudinaryServiceImpl(CloudinaryConfiguration cloudinaryConfiguration) {
        this.cloudinaryConfiguration = cloudinaryConfiguration;
    }


    @Override
    public String uploadFile(MultipartFile file) {
        try {

            File uploadedFile = convertMultiPartToFile(file);
            Map uploadResult = cloudinaryConfiguration.cloudinary().uploader().upload(uploadedFile, ObjectUtils.emptyMap());

            boolean isDeleted = uploadedFile.delete();

            if (isDeleted) {
                AppUtils.logger("INFO", "File successfully deleted");
            } else
                AppUtils.logger("WARN", "File doesn't exist");

            return uploadResult.get("secure_url").toString();

        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private File convertMultiPartToFile(MultipartFile file) throws IOException {
        File convFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        FileOutputStream fos = new FileOutputStream(convFile);
        fos.write(file.getBytes());
        fos.close();
        return convFile;
    }
}
