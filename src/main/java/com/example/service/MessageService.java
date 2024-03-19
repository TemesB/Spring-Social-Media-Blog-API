package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;


@Service
public class MessageService {
    @Autowired 
    private MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    public Message createMessage(Message message) {
        Optional<Message>existingMessage = messageRepository.findMessageByposted_by(message.getPosted_by());
        if(existingMessage.isPresent()){
            return messageRepository.save(message);
        }else{
        return null;
        }
    }
    public List<Message> getAllMessages(){
       return messageRepository.findAll();
}
public Message findMessageByMessage_id(int message_id){
    Optional<Message> optionalMess = messageRepository.findMessageByMessage_Id(message_id);
    if(optionalMess.isPresent()){
        return optionalMess.get();
    }else{
        return null;
    }
}
public boolean deleteMessageByMessageId(int message_id) {
    Optional<Message> optionalMessage = messageRepository.findById(message_id);

    if (optionalMessage.isPresent()) {
        messageRepository.deleteById(message_id);
        return true;
    } else {
        return false;
    }
}
public List<Message> getMessagesforUser(int id){
    System.out.println("ABOUT TO CREATE");
    Optional<List<Message>> optionalMess = messageRepository.findMessagesByposted_by(id);
    if(optionalMess.isPresent()){
        return optionalMess.get();
    }else{
        return null;
    }
}
public Message updateMessageByMessage_id(int message_id, Message text){
       
    Optional<Message> optionalMess = messageRepository.findMessageByMessage_Id(message_id);
   
    if(optionalMess.isPresent()){
        Message up = messageRepository.findMessageByMessage_Id2(message_id);
        up.setMessage_text(text.getMessage_text());
        messageRepository.save(up);
        System.out.println("ABOUT TO CREATE" + up);
        return up;
    }else{
        return null;
    }
}
}

