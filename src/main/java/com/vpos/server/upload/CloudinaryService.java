package com.vpos.server.upload;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author Rithy SKUN
 * @created 19/04/2023 - 9:56 AM
 * @project server
 **/

public interface CloudinaryService {
    String uploadFile(MultipartFile file);
}
