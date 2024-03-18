package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
public AccountService (AccountRepository accountRepository){
    this.accountRepository = accountRepository;
}
    public Account createAccount(Account account){
        Optional<Account> duplicAcct = accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
        if(duplicAcct.isPresent()){
            return null;
        } else{
            return accountRepository.save(account);
        }
    
    }
}
