package com.square_health.blog.DAO;


import com.square_health.blog.Entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDao extends CrudRepository<UserEntity, Long> {
    UserEntity findById(int id);
    Optional<UserEntity> findByEmail(String email);
    UserEntity findByEmailAndPassword(String email, String password);
    UserEntity findByEmailAndRoleId(String email, int roleId);
    List<UserEntity> findByRoleId(int roleId);
}
