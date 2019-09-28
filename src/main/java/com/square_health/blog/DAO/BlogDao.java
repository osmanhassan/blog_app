package com.square_health.blog.DAO;

import com.square_health.blog.Entity.BlogsEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BlogDao extends CrudRepository<BlogsEntity, Integer> {
    List<BlogsEntity> findByIsApproved(boolean isApproved);
    List<BlogsEntity> findByUserIdAndIsApproved(int userId, boolean isApproved);
    BlogsEntity findById(int id);

}
