package com.square_health.blog.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "likes_unlikes")
public class LikesUnlikesEntity {
    private int id;
    private String type;
    private int userId;
    private int blogId;
    private UserEntity userByUserId;
    private BlogsEntity blogByBlogId;

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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
    @Column(name = "blog_id", insertable = false, updatable = false)
    public int getBlogId() {
        return userId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LikesUnlikesEntity that = (LikesUnlikesEntity) o;
        return id == that.id &&
                Objects.equals(type, that.type) &&
                Objects.equals(userId, that.userId) &&
                Objects.equals(blogId, that.blogId) &&
                Objects.equals(blogByBlogId, that.blogByBlogId) &&
                Objects.equals(userByUserId, that.userByUserId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, type, userId, userByUserId, blogId, blogByBlogId);
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


    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "blog_id", referencedColumnName = "id", nullable = false)
    public BlogsEntity getBlogByBlogId() {
        return blogByBlogId;
    }

    public void setBlogByBlogId(BlogsEntity blogByBlogId) {
        this.blogByBlogId = blogByBlogId;
    }


}
