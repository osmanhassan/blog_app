package com.square_health.blog.Service;

import com.square_health.blog.DAO.BlogDao;
import com.square_health.blog.DAO.LikesUnlikesDao;
import com.square_health.blog.DAO.UserDao;
import com.square_health.blog.DTO.LikesUnlikesDTO;
import com.square_health.blog.Entity.BlogsEntity;
import com.square_health.blog.Entity.LikesUnlikesEntity;
import com.square_health.blog.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesUnlikesService {
    @Autowired
    LikesUnlikesDao likesUnlikesDao;
    @Autowired
    UserDao userDao;
    @Autowired
    BlogDao blogDao;
    @Autowired
    BlogService blogService;

    public Object addLikesUnlikes(LikesUnlikesDTO likesUnlikesDTO){

        int blogId = likesUnlikesDTO.getBlogId();

        if(blogService.getById(blogId) != null){

            BlogsEntity blog = blogService.getById(blogId);

            if(blogService.isApprovedBlog(blog) && ! blogService.isMyBlog(blog)) {

                int userId = likesUnlikesDTO.getUserId();
                String type = likesUnlikesDTO.getType();
                LikesUnlikesEntity likesUnlikesEntity = null;

                try {
                    likesUnlikesEntity = likesUnlikesDao.findByUserIdAndBlogIdAndType(userId, blogId, type);
                } catch (Exception e) {
                }

                if (likesUnlikesEntity == null) {
                    UserEntity user = userDao.findById(userId);
                    likesUnlikesEntity = getLikesUnlikesEntityFromDTO(likesUnlikesDTO, blog, user);
                    likesUnlikesDao.save(likesUnlikesEntity);

                    LikesUnlikesEntity alternativeLikesUnlikesEntity = null;

                    String alternateType = "ul";
                    if(likesUnlikesDTO.getType().equals("ul"))
                        alternateType = "l";

                    try {
                        alternativeLikesUnlikesEntity = likesUnlikesDao.findByUserIdAndBlogIdAndType(userId, blogId, alternateType);
                    } catch (Exception e) {
                    }

                    if(alternativeLikesUnlikesEntity != null){
                        likesUnlikesDao.delete(alternativeLikesUnlikesEntity);
                    }

                }

            }

        }

        return blogService.blogList();
    }

    public LikesUnlikesEntity getLikesUnlikesEntityFromDTO(LikesUnlikesDTO likesUnlikesDTO, BlogsEntity blog, UserEntity user){
        LikesUnlikesEntity likesUnlikesEntity = new LikesUnlikesEntity();
        likesUnlikesEntity.setBlogId(likesUnlikesDTO.getBlogId());
        likesUnlikesEntity.setUserId(likesUnlikesDTO.getUserId());
        likesUnlikesEntity.setType(likesUnlikesDTO.getType());
        likesUnlikesEntity.setBlogByBlogId(blog);
        likesUnlikesEntity.setUserByUserId(user);
        return likesUnlikesEntity;
    }
}
