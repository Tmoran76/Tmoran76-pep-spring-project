package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;


@Service
public class MessageService {

    MessageRepository messageRepository;
    @Autowired
    public MessageService(MessageRepository messageRepository){
        this.messageRepository = messageRepository;
    }
    public List<Message> allMessage(){
        return messageRepository.findAll();
    }
    public ResponseEntity<Message> createMessage(Message message){
        List<Message> allMessages = allMessage();
        if((message.getMessage_text().length() < 255) && (!message.getMessage_text().isEmpty())){
            for(Message m: allMessages){
                if(message.getPosted_by().equals(m.getPosted_by())){
                    Message newMessage = messageRepository.save(message);
                    return ResponseEntity.status(200).body(newMessage);
                }
                
            }
        }
        return ResponseEntity.status(400).body(null);
    }
    public Message getMessageByID(int message_id){
        List<Message> allMessages = allMessage();
        for(Message m: allMessages){
            if(m.getMessage_id().equals(message_id)){
                return m;
            }
        }
        return null;

    }
    public Integer deleteMessage(int message_id){
        List<Message> allMessages = allMessage();
        for(Message m: allMessages){
            if(m.getMessage_id().equals(message_id)){
                messageRepository.delete(m);
                return 1;
            }
        }
        return null;
    }
    

}
