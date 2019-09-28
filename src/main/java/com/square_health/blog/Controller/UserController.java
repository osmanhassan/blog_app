package com.square_health.blog.Controller;

import com.square_health.blog.DAO.BlogDao;
import com.square_health.blog.DAO.RoleDao;
import com.square_health.blog.DAO.UserDao;
import com.square_health.blog.DTO.UserDTO;
import com.square_health.blog.Service.UpUserService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@RestController
@CrossOrigin
@RequestMapping(value = "/users/")
public class UserController {

    @Autowired
    UpUserService upUserService;
    @Autowired
    UserDao userDao;
    @Autowired
    BlogDao blogDao;
    @Autowired
    RoleDao roleDao;

    @GetMapping("/list")
    public Object userList(HttpServletRequest request){
        return upUserService.getAllUsers();
    }

    @PostMapping(value = "/is_unique_email")
    public Object UniqueEmailChecker(@RequestBody UserDTO userDTO){
        return upUserService.isUniqueEmail(userDTO.getEmail());
    }

    @PostMapping("/add")
    public Object save(@RequestBody UserDTO userDTO) throws Exception {
        return upUserService.saveUser(userDTO, 2);
    }

    @PostMapping("/admin/add")
    public Object saveAdmin(@RequestBody UserDTO userDTO) throws Exception {
        return upUserService.saveUser(userDTO, 1);
    }

    @PostMapping(value = "/change-approval")
    public Object changeApproval(@RequestBody UserDTO userDTO){
        return upUserService.changeApproval(userDTO);
    }

}
