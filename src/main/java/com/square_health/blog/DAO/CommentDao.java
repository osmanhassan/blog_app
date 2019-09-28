package com.square_health.blog.DAO;

import com.square_health.blog.Entity.CommentsEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentDao extends CrudRepository<CommentsEntity, Integer> {

}
