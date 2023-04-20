package com.vpos.server.upload.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Rithy SKUN
 * @created 20/04/2023 - 9:45 AM
 * @project server
 **/

@Repository
public interface FileStorageRepository extends JpaRepository<FileStorage, Long> {
}
