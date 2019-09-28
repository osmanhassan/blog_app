package com.square_health.blog.Controller;

import com.square_health.blog.DAO.BlogDao;
import com.square_health.blog.DAO.UserDao;
import com.square_health.blog.DTO.BlogDTO;
import com.square_health.blog.Entity.BlogsEntity;
import com.square_health.blog.Service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/blog")
@CrossOrigin
public class BlogController {

    @Autowired
    BlogDao blogDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BlogService blogService;

    @PostMapping(value = "/add")
    public Object saveBlog(@RequestBody BlogDTO blogDTO){
        return blogService.SaveBlog(blogDTO);
    }
    @GetMapping(value = "/list_all")
    public Object list(){
        return blogService.blogList();
    }

    @PostMapping(value = "/change-approval")
    public Object changeApproval(@RequestBody BlogDTO blogDTO){
        BlogsEntity blog = blogDao.findById(blogDTO.getId());
        blog.setIsApproved(blogDTO.getIsApproved());
        blogDao.save(blog);
        return blogService.blogList();
    }

    @PostMapping(value = "/delete")
    public Object delete(@RequestBody BlogDTO blogDTO){
        return blogService.deleteBlog(blogDTO);
    }


}
