package com.example.repository;

import java.util.Optional;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
    // Create custom method to create new account
   public Account findByUsername(String username);
    // Naming convention
    Optional<Account> findByUsernameAndPassword(String name, String password);

    
}
