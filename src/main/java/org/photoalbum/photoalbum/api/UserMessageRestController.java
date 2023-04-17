package org.photoalbum.photoalbum.api;

import jakarta.validation.Valid;
import org.photoalbum.photoalbum.model.UserMessage;
import org.photoalbum.photoalbum.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/user-message")
@CrossOrigin
public class UserMessageRestController {

    @Autowired
    private UserMessageService userMessageService;

    @PostMapping
    public ResponseEntity create(@Valid @RequestBody UserMessage formUserMessage, BindingResult bindingResult){
        if (bindingResult.hasErrors()) return new ResponseEntity<>(bindingResult.getAllErrors().stream().map(error -> error.getDefaultMessage()).collect(Collectors.toList()),
                HttpStatus.BAD_REQUEST);
            //throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(userMessageService.sendMessage(formUserMessage), HttpStatus.OK);
    }
}
