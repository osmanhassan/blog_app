package com.square_health.blog.DAO;

import com.square_health.blog.Entity.LikesUnlikesEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikesUnlikesDao extends CrudRepository<LikesUnlikesEntity, Integer> {
    LikesUnlikesEntity findByUserIdAndBlogIdAndType(int userId, int blogId, String type);
}
