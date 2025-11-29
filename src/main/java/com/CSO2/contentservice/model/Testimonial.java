package com.CSO2.contentservice.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;

@Document(collection = "testimonials")
public class Testimonial {
    @Id
    private String id;
    private String name;
    private String role;
    private String company;
    private int rating;
    private String text;
    private String avatar; // Initials or URL
    private LocalDateTime createdAt;

    public Testimonial() {
        this.createdAt = LocalDateTime.now();
    }

    public Testimonial(String name, String role, String company, int rating, String text, String avatar) {
        this.name = name;
        this.role = role;
        this.company = company;
        this.rating = rating;
        this.text = text;
        this.avatar = avatar;
        this.createdAt = LocalDateTime.now();
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
