package com.square_health.blog.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.hibernate.annotations.Fetch;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "user")
public class UserEntity {
    private int id;
    @NotBlank(message = "First Name is required")
    private String firstName;

    @NotBlank(message = "Last Name is required")
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email! Please enter valid email")
    private String email;

    private int roleId;

    @NotBlank(message = "Password is required")
    private String password;

    private String token;

    private RoleEntity roleByRoleId;

    private boolean isEnabled = false;

    private Collection<BlogsEntity> blogsById;

    private Collection<LikesUnlikesEntity> likesUnlikesById;

    public UserEntity(UserEntity users) {
        this.id = users.id;
        this.firstName = users.firstName;
        this.lastName = users.lastName;
        this.roleId = users.roleId;
        this.roleByRoleId = users.roleByRoleId;
        this.blogsById = users.blogsById;
        this.likesUnlikesById = users.likesUnlikesById;
        this.password = users.password;
        this.token = users.token;
        this.email = users.email;
        this.isEnabled = users.isEnabled;
    }
    public UserEntity(){}

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
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "role_id", insertable = false, updatable = false)
    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    @Basic
    @Column(name = "password")
    @JsonIgnore
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "token")
    @JsonIgnore
    public String getToken() {
        return token;
    }
    public void setToken(String token) {
        this.token = token;
    }



    @Basic
    @Column(name = "is_enabled")

    public boolean getIsEnabled() {
        return this.isEnabled;
    }
    public void setIsEnabled(boolean enabled) {
        isEnabled = enabled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserEntity that = (UserEntity) o;
        return id == that.id &&
                roleId == that.roleId &&
                Objects.equals(firstName, that.firstName) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(email, that.email) &&
                Objects.equals(password, that.password) &&
                Objects.equals(roleId, that.roleId) &&
                Objects.equals(isEnabled, that.isEnabled) &&
                Objects.equals(blogsById, that.blogsById) &&
                Objects.equals(likesUnlikesById, that.likesUnlikesById) &&
                Objects.equals(roleByRoleId, that.roleByRoleId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, firstName, lastName, email, roleId, roleByRoleId, password, isEnabled, blogsById);
    }

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)
    public RoleEntity getRoleByRoleId() {
        return roleByRoleId;
    }

    public void setRoleByRoleId(RoleEntity roleByRoleId) {
        this.roleByRoleId = roleByRoleId;
    }

    @OneToMany(mappedBy = "userByUserId")
    @JsonBackReference
    public Collection<BlogsEntity> getBlogsById() {
        return blogsById;
    }

    public void setBlogsById(Collection<BlogsEntity> blogsById) {
        this.blogsById = blogsById;
    }

    @OneToMany(mappedBy = "userByUserId")
    @JsonBackReference
    public Collection<LikesUnlikesEntity> getLikesUnlikesById() {
        return likesUnlikesById;
    }

    public void setLikesUnlikesById(Collection<LikesUnlikesEntity> likesUnlikesById) {
        this.likesUnlikesById = likesUnlikesById;
    }


}
