package com.example.service;

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
}
