package org.photoalbum.photoalbum.controller;

import org.photoalbum.photoalbum.service.UserMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class UserMessageController {

    @Autowired
    private UserMessageService userMessageService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("messages", userMessageService.getAllMessages());
        return "/messages/index";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        userMessageService.deleteMessage(id);
        return "redirect:/message";
    }
}
