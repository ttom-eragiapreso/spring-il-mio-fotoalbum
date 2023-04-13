package org.photoalbum.photoalbum.controller;

import org.photoalbum.photoalbum.exception.PhotoNotFoundException;
import org.photoalbum.photoalbum.model.Photo;
import org.photoalbum.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> keyword){
        keyword.ifPresent(s -> model.addAttribute("keyword", s));
        model.addAttribute("photos", photoService.getAll(keyword));
        return "/photo/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){

        try {
           Photo photo = photoService.getById(id);
           model.addAttribute("photo", photo);
           return "/photo/show";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
