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
    public Account createAccount(Account account) {
        // Check if an account with the given username already exists
        Account existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount != null) {
            return null;
        } else {
            // Save the new account
            return accountRepository.save(account);
        }
    }
    
    public Account loginAccount (Account account){
        Optional<Account> optionalAccount = accountRepository.findByUsernameAndPassword(account.getUsername(),account.getPassword());
        if(optionalAccount.isPresent()){
            return optionalAccount.get();
        }else return null;

    }
}
