package com.CSO2.contentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "blog_posts")
public class BlogPost {

    @Id
    private String id;
    private String title;
    private String slug;
    private String content; // HTML or Markdown
    private String excerpt;
    private String author;
    private String image;
    private String category;
    private Boolean featured;
    private List<String> tags;
    private Boolean isPublished;
    private LocalDateTime publishDate;

    public BlogPost() {
    }

    public BlogPost(String title, String slug, String content, String excerpt, String author, String image,
            String category, Boolean featured, List<String> tags, Boolean isPublished,
            LocalDateTime publishDate) {
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.excerpt = excerpt;
        this.author = author;
        this.image = image;
        this.category = category;
        this.featured = featured;
        this.tags = tags;
        this.isPublished = isPublished;
        this.publishDate = publishDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getExcerpt() {
        return excerpt;
    }

    public void setExcerpt(String excerpt) {
        this.excerpt = excerpt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getFeatured() {
        return featured;
    }

    public void setFeatured(Boolean featured) {
        this.featured = featured;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Boolean getPublished() {
        return isPublished;
    }

    public void setPublished(Boolean published) {
        isPublished = published;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }
}
