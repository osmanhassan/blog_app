package com.square_health.blog.Service;

import com.square_health.blog.DAO.CommentDao;
import com.square_health.blog.DTO.CommentDTO;
import com.square_health.blog.Entity.BlogsEntity;
import com.square_health.blog.Entity.CommentsEntity;
import com.square_health.blog.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CommentService {
    @Autowired
    BlogService blogService;
    @Autowired
    CommentDao commentDao;
    @Autowired
    UpUserService upUserService;


    public Object save(CommentDTO commentDTO){
        BlogsEntity blog = blogService.getById(commentDTO.getBlogId());
        UserEntity user = upUserService.getLoggedInUser();
        if(blog.getIsApproved()) {
            CommentsEntity commentsEntity = new CommentsEntity();
            commentsEntity.setBlogId(commentDTO.getBlogId());
            commentsEntity.setDescription(commentDTO.getDescription());
            commentsEntity.setUserId(user.getId());
            commentsEntity.setBlogByBlogId(blog);
            commentsEntity.setUserByUserId(user);
            commentDao.save(commentsEntity);
        }
        return blogService.blogList();
    }
}
