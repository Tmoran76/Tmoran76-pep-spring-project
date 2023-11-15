package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
    AccountService accountService;
    MessageService messageService;
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<Account> postAccount(@RequestBody Account account){
        return accountService.registerUser(account);
    }
    @GetMapping("/messages")
    public List<Message> getMessages(){
        List<Message> allMessage = messageService.allMessage();
        return allMessage;
    }
    @PostMapping("/login")
    public ResponseEntity<Account> accountLogin(@RequestBody Account account){
        
        return accountService.checkUsernamePassword(account.getUsername(), account.getPassword());
    }
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message){
        return messageService.createMessage(message);
    }
    @GetMapping("/messages/{message_id}")
    public Message getMessageByID(@PathVariable int message_id){
        return messageService.getMessageByID(message_id);
    }
    @DeleteMapping("/messages/{message_id}")
    public Integer deleteMessageByID(@PathVariable int message_id){
        return messageService.deleteMessage(message_id);
    }
}
