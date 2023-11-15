package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.function.EntityResponse;

import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {
    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }
    public Account persistAccount(Account account){
        return accountRepository.save(account);
    }
    public ResponseEntity<Account> checkUsernamePassword(String username, String password){    
        
        List<Account> allAccounts = accountRepository.findAll();
        for(Account a: allAccounts){
            if(username.equals(a.getUsername()) && password.equals(a.getPassword())){
                return ResponseEntity.status(200).body(a);
            }
        }
        return ResponseEntity.status(401).body(null);
    }
    public ResponseEntity<Account> registerUser(Account account){
        List<Account> allAccounts = accountRepository.findAll();
        if((!account.getUsername().isBlank()) && (account.getPassword().length()>4)){
            for(Account a: allAccounts){
                if(a.getUsername().equals(account.getUsername())){
                    return ResponseEntity.status(409).body(null);
                }
                else{
                    Account newAccount = accountRepository.save(account);
                    return ResponseEntity.status(200).body(newAccount);
                }
            }
        }
        
        return ResponseEntity.status(400).body(null);
        

    }
}
    
    

    

    
    

