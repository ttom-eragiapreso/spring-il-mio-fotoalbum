package org.photoalbum.photoalbum.controller;

import jakarta.validation.Valid;
import org.photoalbum.photoalbum.dto.FileDTO;
import org.photoalbum.photoalbum.exception.PhotoNotFoundException;
import org.photoalbum.photoalbum.model.Photo;
import org.photoalbum.photoalbum.service.CategoryService;
import org.photoalbum.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/photo")
public class PhotoController {

    @Autowired
    private PhotoService photoService;
    @Autowired
    private CategoryService categoryService;

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

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        try {
            photoService.deletePhoto(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return "redirect:/photo";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){

        try {
            Photo photoToEdit = photoService.getById(id);
            FileDTO fileDTO = new FileDTO(photoToEdit);
            model.addAttribute("categories", categoryService.getAllCategories());
            model.addAttribute("photo", fileDTO);
            return "/photo/create-edit";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @ModelAttribute(name = "photo") FileDTO photo, BindingResult bindingResult){

        if(bindingResult.hasErrors()) return "/photo/create-edit";

        try {
            photoService.updatePhoto(photo, id);
            return "redirect:/photo";
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("photo", new FileDTO());
        return "/photo/create-edit";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute FileDTO formPhoto, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()) return "/photo/create-edit";

       Photo newPhoto = photoService.storePhoto(formPhoto);

       return "redirect:/photo/" + newPhoto.getId();
    }

}
