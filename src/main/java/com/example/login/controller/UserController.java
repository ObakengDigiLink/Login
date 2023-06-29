package com.example.login.controller;

import com.example.login.model.Admin;
import com.example.login.model.User;
import com.example.login.repo.AdminRepo;
import com.example.login.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private UserRepo userRepo;


    public UserController(UserRepo userRepo) {

        this.userRepo = userRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User userData){
        User user= userRepo.findByEmail(userData.getEmail());

        if(user.getPassword().equals(userData.getPassword())){
            return ResponseEntity.ok(user);
        }
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email){
        if(userRepo.findByEmail(email) != null){
            return new ResponseEntity<>(userRepo.findByEmail(email), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
