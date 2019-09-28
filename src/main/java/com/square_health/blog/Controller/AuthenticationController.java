package com.square_health.blog.Controller;

import java.util.Objects;

import com.square_health.blog.Config.JwtTokenUtil;
import com.square_health.blog.DTO.UserDTO;
import com.square_health.blog.Entity.UserEntity;
import com.square_health.blog.Service.EncryptionService;
import com.square_health.blog.Service.UpUserService;
import com.square_health.blog.Service.UserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin

public class AuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    UpUserService upUserService;
    @Autowired
    EncryptionService encryptionService;

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody UserDTO authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails);
        UserEntity user = upUserService.getByUserEmailAndPassword(authenticationRequest.getEmail(),
                encryptionService.encrypt(authenticationRequest.getPassword()));
        user.setToken(token);
        return ResponseEntity.ok(getResponse(user));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public String getResponse(UserEntity user){
        String role = "User";
        if(user.getRoleId() == 1){
            role = "Admin";
        }
        return "{" +
                "\"id\": " + user.getId() + "," +
                "\"firstName\": \"" + user.getFirstName() + "\"," +
                "\"lastName\": \"" + user.getLastName() + "\"," +
                "\"username\": \"" + user.getEmail() + "\"," +
                "\"role\": \"" + role + "\"," +
                "\"password\": \" \"," +
                "\"token\": \"" + user.getToken() + "\"" +
                "}";
    }
}
