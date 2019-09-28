package com.square_health.blog.Controller;

import com.square_health.blog.DTO.LikesUnlikesDTO;
import com.square_health.blog.Service.LikesUnlikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping(value = "/likesunlikes")
public class LikesUnlikesController {

    @Autowired
    LikesUnlikesService likesUnlikesService;

    @PostMapping(value = "/add")
    public Object add(@RequestBody LikesUnlikesDTO likesUnlikesDTO){
        return likesUnlikesService.addLikesUnlikes(likesUnlikesDTO);
    }
}
