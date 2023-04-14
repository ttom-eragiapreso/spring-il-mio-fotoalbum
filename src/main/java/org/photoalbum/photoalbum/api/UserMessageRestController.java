package org.photoalbum.photoalbum.api;

import jakarta.validation.Valid;
import org.photoalbum.photoalbum.model.UserMessage;
import org.photoalbum.photoalbum.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/user-message")
@CrossOrigin
public class UserMessageRestController {

    @Autowired
    private UserMessageService userMessageService;

    @PostMapping
    public void create(@Valid @RequestBody UserMessage formUserMessage, BindingResult bindingResult){
        if (bindingResult.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        userMessageService.sendMessage(formUserMessage);
    }
}
