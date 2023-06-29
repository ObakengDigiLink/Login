package com.example.login.repo;

import com.example.login.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepo extends JpaRepository<Admin, Long > {
    Admin findByEmail(String email);
}
