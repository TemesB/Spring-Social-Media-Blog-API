package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping()
public class SocialMediaController {
    private AccountService accountService;
    private MessageService messageService;
@Autowired
public SocialMediaController(AccountService accountService, MessageService messageService){
        this.accountService = accountService;
        this.messageService = messageService;
    }
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account newAccount ){
        Account createdAccount = new Account(newAccount.getUsername(), newAccount.getPassword());
        Account validateRegistration = accountService.createAccount(createdAccount);
        if(newAccount.getUsername() == null ||  newAccount.getPassword().length() <= 4){
            validateRegistration = null;
        }
        if(validateRegistration != null){
            return ResponseEntity.status(200)
     .body(validateRegistration);
    }else{
        return ResponseEntity.status(409).body(validateRegistration);
     } 
    }

    @PostMapping("/login")
    public  ResponseEntity<Account> login( @RequestBody Account acc ) {
       
        Account account = new Account(acc.getUsername(), acc.getPassword());
       
       Account test = accountService.loginAccount(account);
     if(test != null){
  return ResponseEntity.status(200)
     .body(test);
     }else{
        return ResponseEntity.status(401).body(test);
     }
    }

    // Create Message
    @PostMapping("/messages")
    public ResponseEntity<Message> postMessage(@RequestBody Message message) {
        // Check if the message text is not blank and does not exceed 255 characters
        if (isValidMessage(message)) {
            Message createdMessage = messageService.createMessage(message);
            if (createdMessage != null) {
                return ResponseEntity.ok(createdMessage);
            } else {
            return ResponseEntity.badRequest().build();
          
            }
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    // Helper method to validate the message
    private boolean isValidMessage(Message message) {
        return message != null &&
               message.getMessage_text() != null &&
               !message.getMessage_text().isBlank() &&
               message.getMessage_text().length() <= 255;
    }
    @GetMapping("/messages")
   public ResponseEntity<?> retrieveAllMessages(){
    return new ResponseEntity<>(messageService.getAllMessages(),HttpStatus.OK);
   }
   @GetMapping("messages/{message_id}")
   public ResponseEntity<Message> getMessage(@PathVariable Integer message_id) {
    Message grabMessage = messageService.findMessageByMessage_id(message_id);
    return ResponseEntity.status(200)
    .body(grabMessage);
   }

   @DeleteMapping   ("messages/{message_id}")
   public ResponseEntity<Integer> deleteMessage(@PathVariable Integer message_id) {
    boolean messageDeleted = messageService.deleteMessageByMessageId(message_id);
    if (messageDeleted) {
        return ResponseEntity.ok().body(1);
    } else {
        return ResponseEntity.ok().body(null);
    }
}
}
