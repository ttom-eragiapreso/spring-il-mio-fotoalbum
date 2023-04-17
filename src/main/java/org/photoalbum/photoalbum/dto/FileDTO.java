package org.photoalbum.photoalbum.dto;

import jakarta.persistence.OneToOne;
import org.photoalbum.photoalbum.model.Category;
import org.photoalbum.photoalbum.model.Photo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class FileDTO {

    private MultipartFile multipartFile;


    private Integer id;
    private String title;

    private String description;

    private String url;

    private Boolean visible;

    private List<Category> categories;

    public FileDTO(Photo photo) {
        this.id = photo.getId();
        this.title = photo.getTitle();
        this.description = photo.getDescription();
        this.url = photo.getUrl();
        this.visible = photo.getVisible();
        this.categories = photo.getCategories();
    }

    public FileDTO() {
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}
