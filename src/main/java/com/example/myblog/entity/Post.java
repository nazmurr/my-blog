package com.example.myblog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Date;

@Entity
@Table(name = "post")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Title is required")
    @Size(min = 10, message = "Title is required")
    @Column(name = "title")
    private String title;

    @Column(name = "slug")
    private String slug;

    @Column(name = "content")
    private String content;

    @Column(name = "excerpt")
    private String excerpt;

    @Column(name = "featured_img_url")
    private String featuredImgUrl;

    @Column(name = "status")
    private String status;

    @Column(name="created_at")
    private Date createdAt;

    @Column(name="updated_at")
    private Date updatedAt;

    @ManyToOne(fetch = FetchType.LAZY,
            cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name="user_id")
    private User user;

    public Post() {

    }

    public Post(String title, String slug, String content, String excerpt, String featuredImgUrl, String status, Date createdAt, Date updatedAt) {
        this.title = title;
        this.slug = slug;
        this.content = content;
        this.excerpt = excerpt;
        this.featuredImgUrl = featuredImgUrl;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public String getFeaturedImgUrl() {
        return featuredImgUrl;
    }

    public void setFeaturedImgUrl(String featuredImgUrl) {
        this.featuredImgUrl = featuredImgUrl;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", slug='" + slug + '\'' +
                ", content='" + content + '\'' +
                ", excerpt='" + excerpt + '\'' +
                ", featuredImgUrl='" + featuredImgUrl + '\'' +
                ", status='" + status + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                //", user=" + user +
                '}';
    }
}
