package org.photoalbum.photoalbum.service;

import org.photoalbum.photoalbum.exception.PhotoNotFoundException;
import org.photoalbum.photoalbum.exception.UserMessageNotFoundException;
import org.photoalbum.photoalbum.model.Photo;
import org.photoalbum.photoalbum.model.UserMessage;
import org.photoalbum.photoalbum.repository.UserMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class UserMessageService {

    @Autowired
    private UserMessageRepository userMessageRepository;

    public List<UserMessage> getAllMessages(){
        return userMessageRepository.findAll();
    }

    public UserMessage getById(Integer id) throws ResponseStatusException {
        return userMessageRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void sendMessage(UserMessage formUserMessage){
        UserMessage userMessageToSave = new UserMessage();

        userMessageToSave.setContent(formUserMessage.getContent());
        userMessageToSave.setEmail(formUserMessage.getEmail());

        userMessageRepository.save(userMessageToSave);
    }

    public void deleteMessage(Integer id){
        UserMessage userMessageToDelete = getById(id);

        userMessageRepository.delete(userMessageToDelete);
    }
}
