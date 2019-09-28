package com.square_health.blog.DAO;


import com.square_health.blog.Entity.RoleEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends CrudRepository<RoleEntity, Long> {
    RoleEntity findById(int roleId);
}
