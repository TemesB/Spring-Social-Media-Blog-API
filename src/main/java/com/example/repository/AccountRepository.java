package com.example.repository;

import java.util.Optional;

import javax.persistence.Id;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>{
     // Defiene methods that follow query naming convention
    Account findByUsername(String username);
    Optional<Account> findByUsernameAndPassword(String name, String password);

    
}
