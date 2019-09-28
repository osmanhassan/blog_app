package com.square_health.blog.Service;


import com.square_health.blog.DAO.RoleDao;
import com.square_health.blog.DAO.UserDao;
import com.square_health.blog.DTO.UserDTO;
import com.square_health.blog.Entity.CustomUserDetails;
import com.square_health.blog.Entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
public class UpUserService {
    @Autowired
    UserDao userDao;
    @Autowired
    RoleDao roleDao;
    @Autowired
    EncryptionService encryptionService;

    public UserEntity saveUser(UserDTO userDTO, int roleId) throws Exception {
        UserEntity userEntity = new UserEntity();
        if(roleId == 2)
            userEntity.setIsEnabled(false);
        else
            userEntity.setIsEnabled(true);
        userEntity.setEmail(userDTO.getEmail());
        userEntity.setRoleId(roleId);
        userEntity.setPassword(encryptionService.encrypt(userDTO.getPassword()));
        userEntity.setFirstName(userDTO.getFirstName());
        userEntity.setLastName(userDTO.getLastName());
        userEntity.setRoleByRoleId(roleDao.findById(roleId));
        return userDao.save(userEntity);
    }

    public Object getAllUsers(){
        return userDao.findAll();
    }
    public Object changeApproval(UserDTO userDTO){
        UserEntity user = userDao.findById(userDTO.getId());
        if(user.getRoleId() == 2) {
            user.setIsEnabled(userDTO.getIsEnabled());
            userDao.save(user);
        }
        return getAllUsers();
    }
    public Object isUniqueEmail(String email){
        if(! userDao.findByEmail(email).isPresent()){
            return true;
        }
        return false;
    }

    public UserEntity getLoggedInUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  (CustomUserDetails) authentication.getPrincipal();
    }

    public boolean currentUserIsAdmin(){
        return getLoggedInUser().getRoleId() == 1;
    }

    public UserEntity getByUserEmailAndPassword(String email, String password){
        return userDao.findByEmailAndPassword(email, password);
    }


}
