package com.example.Project.controllers;

import com.example.Project.dtos.LoginAuth;
import com.example.Project.dtos.LoginDTO;
import com.example.Project.servises.LoginService;
import com.example.Project.tables.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/login-auth")
@CrossOrigin
public class LoginController {
    @Autowired
    private LoginService loginServices;

     @PostMapping("/login")
    public ResponseEntity<Login> loginAuthentication(@RequestBody LoginAuth login) {
        try {
            Login authenticatedUser = loginServices.login_authentication(login);
            return ResponseEntity.ok(authenticatedUser);
        } catch (ResponseStatusException e) {
            return new ResponseEntity<>(null, e.getStatusCode());
        }
    }

    @PostMapping("/registration")
    public ResponseEntity<Login> SaveUser(@RequestBody LoginDTO dto){
        Login lgn = loginServices.saveUsers(dto);
        return new ResponseEntity<Login>(lgn,new HttpHeaders(), HttpStatus.OK);
    }

    @PutMapping("/updateLoginData/{user_id}")
    public Optional<Login> updateLoginData(@PathVariable("user_id") String user_id, @RequestBody LoginDTO dto){
        return loginServices.updateLoginData(user_id,dto);
    }
    @PutMapping("/resetPassword/{user_id}")
    public Optional<Login> resetPassword(@PathVariable("user_id") String user_id,@RequestBody LoginDTO dto){
        return loginServices.resetPassword(user_id,dto);
    }
    @GetMapping("/getLoginInformationByUserId/{user_id}")
    public Login getLoginInformationByUserId(@PathVariable("user_id") String user_id){
        return loginServices.getLoginDataByUserId(user_id);
    }

    @GetMapping("/getAllUsers")
    public List<Login> getAllUsers(){
        return loginServices.getAllUsers();
    }
}
