package com.square_health.blog.Controller;

import com.square_health.blog.DTO.CommentDTO;
import com.square_health.blog.Service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/add")
    public Object save(@RequestBody CommentDTO commentDTO){
        return commentService.save(commentDTO);
    }
}
