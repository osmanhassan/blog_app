package com.square_health.blog.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "blogs")
public class BlogsEntity {
    private int id;
    private String description;
    private int userId;
    private UserEntity userByUserId;
    private Collection<CommentsEntity> commentsById;
    private Collection<LikesUnlikesEntity> likesUnlikesById;
    private boolean isApproved = false;

    @Id
    @GeneratedValue
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic
    @Column(name = "user_id", insertable = false, updatable = false)
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "is_approved")
    public boolean getIsApproved() {
        return isApproved;
    }
    public void setIsApproved(boolean isApproved) {
        this.isApproved = isApproved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogsEntity that = (BlogsEntity) o;
        return id == that.id &&
                Objects.equals(description, that.description) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(isApproved, that.isApproved) &&
                Objects.equals(commentsById, that.commentsById) &&
                Objects.equals(likesUnlikesById, that.likesUnlikesById) &&
                Objects.equals(userByUserId, that.userByUserId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, description, userId, userByUserId, isApproved);
    }

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    public UserEntity getUserByUserId() {
        return userByUserId;
    }

    public void setUserByUserId(UserEntity userByUserId) {
        this.userByUserId = userByUserId;
    }

    @OneToMany(mappedBy = "blogByBlogId", cascade = CascadeType.ALL)
    @JsonManagedReference
    public Collection<CommentsEntity> getCommentsById() {
        return commentsById;
    }

    public void setCommentsById(Collection<CommentsEntity> commentsById) {
        this.commentsById = commentsById;
    }

    @OneToMany(mappedBy = "blogByBlogId", cascade = CascadeType.ALL)
    @JsonManagedReference
    public Collection<LikesUnlikesEntity> getLikesUnlikesById() {
        return likesUnlikesById;
    }

    public void setLikesUnlikesById(Collection<LikesUnlikesEntity> likesUnlikesById) {
        this.likesUnlikesById = likesUnlikesById;
    }


}

