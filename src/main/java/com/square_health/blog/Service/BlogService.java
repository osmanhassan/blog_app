package com.square_health.blog.Service;

import com.fasterxml.jackson.databind.util.ArrayIterator;
import com.square_health.blog.DAO.BlogDao;
import com.square_health.blog.DAO.UserDao;
import com.square_health.blog.DTO.BlogDTO;
import com.square_health.blog.Entity.BlogsEntity;
import com.square_health.blog.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class BlogService {
    @Autowired
    BlogDao blogDao;
    @Autowired
    UpUserService upUserService;
    @Autowired
    UserDao userDao;

    public BlogsEntity SaveBlog(BlogDTO blogDTO){
        BlogsEntity blog = new BlogsEntity();
        blog.setUserId(blogDTO.getUserId());
        blog.setDescription(blogDTO.getDescription());
        blog.setIsApproved(false);
        blog.setUserByUserId(userDao.findById(blogDTO.getUserId()));
        return blogDao.save(blog);
    }

    public Object deleteBlog(BlogDTO blogDTO){
        BlogsEntity blog = blogDao.findById(blogDTO.getId());
        if(isMyBlog(blog) || upUserService.currentUserIsAdmin())
            blogDao.delete(blog);
        return blogList();
    }

    public boolean isMyBlog(BlogsEntity blog){
        UserEntity me = upUserService.getLoggedInUser();
        return blog.getUserId() == me.getId();
    }

    public BlogsEntity getById(int blogId){
        return blogDao.findById(blogId);
    }

    public boolean isApprovedBlog(BlogsEntity blog){
        return blog.getIsApproved();
    }

    public Object blogList(){
        if(upUserService.currentUserIsAdmin())
            return blogDao.findAll();
        UserEntity currentUser = upUserService.getLoggedInUser();
        return Stream.concat(blogDao.findByIsApproved(true).stream(),
                blogDao.findByUserIdAndIsApproved(currentUser.getId(), false).stream())
                .collect(Collectors.toList());
    }
}
