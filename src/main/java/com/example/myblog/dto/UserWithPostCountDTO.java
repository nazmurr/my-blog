package com.example.myblog.dto;

import java.util.Date;

public class UserWithPostCountDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean enabled;
    private Date createdAt;
    private Long postCount;

    public UserWithPostCountDTO(
            Long id,
            String firstName,
            String lastName,
            String email,
            boolean enabled,
            Date createdAt,
            Long postCount
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.postCount = postCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Long getPostCount() {
        return postCount;
    }

    public void setPostCount(Long postCount) {
        this.postCount = postCount;
    }

//    @Override
//    public String toString() {
//        return "UserWithPostCountDTO{" +
//                "id=" + id +
//                ", firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", email='" + email + '\'' +
//                ", postCount=" + postCount +
//                '}';
//    }

    @Override
    public String toString() {
        return "UserWithPostCountDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", enabled=" + enabled +
                ", createdAt=" + createdAt +
                ", postCount=" + postCount +
                '}';
    }
}
