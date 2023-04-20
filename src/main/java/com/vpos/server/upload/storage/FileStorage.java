package com.vpos.server.upload.storage;

import jakarta.persistence.*;

import java.util.Date;

/**
 * @author Rithy SKUN
 * @created 20/04/2023 - 9:40 AM
 * @project server
 **/

@Entity
@Table(name = "file_storage")
public class FileStorage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "fileName")
    private String fileName;
    private String fileType;
    private String URL;

    @Column(name = "createAt", updatable = false)
    private Date createdAt;
    private Date updatedAt;

    public FileStorage() {
    }

    public FileStorage(String fileName, String fileType, String URL, Date createdAt, Date updatedAt) {
        this.fileName = fileName;
        this.fileType = fileType;
        this.URL = URL;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "FileStorage{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", URL='" + URL + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
