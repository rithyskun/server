package com.vpos.server.category;

/*
 * @created 11/04/2023 - 4:45 PM
 * @project server
 * @author Rithy SKUN
 */

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "categoryName", nullable = false, unique = true)
    @NotBlank(message = "categoryName is required")
    private String categoryName;

    @Column(name = "categoryImageUrl")
    private String categoryImageUrl;

    @Column(name = "status")
    private Boolean status = true;

    public Category() {
    }

    public Category(String categoryName, String categoryImageUrl, Boolean status) {
        this.categoryName = categoryName;
        this.categoryImageUrl = categoryImageUrl;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImageUrl() {
        return categoryImageUrl;
    }

    public void setCategoryImageUrl(String categoryImageUrl) {
        this.categoryImageUrl = categoryImageUrl;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", categoryName='" + categoryName + '\'' +
                ", categoryImageUrl='" + categoryImageUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
