package com.example.login.controller;

import com.example.login.model.Admin;
import com.example.login.repo.AdminRepo;
import com.example.login.repo.UserRepo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "http://localhost:4200")
public class AdminController {
    private AdminRepo adminRepo;
    private UserRepo userRepo;

    public AdminController(AdminRepo adminRepo ) {
        this.adminRepo = adminRepo;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAdmin(@RequestBody Admin adminData){
        Admin admin= adminRepo.findByEmail(adminData.getEmail());

        if(admin.getPassword().equals(adminData.getPassword())){
            return ResponseEntity.ok(admin);
        }
        return (ResponseEntity<?>) ResponseEntity.ok();
    }

    @GetMapping("/{email}")
    public ResponseEntity<?> getByEmail(@PathVariable("email") String email){
        if(adminRepo.findByEmail(email) != null){
            return new ResponseEntity<>(adminRepo.findByEmail(email), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


}
