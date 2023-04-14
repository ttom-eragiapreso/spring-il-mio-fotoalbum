package org.photoalbum.photoalbum.api;

import jakarta.validation.Valid;
import org.photoalbum.photoalbum.exception.PhotoNotFoundException;
import org.photoalbum.photoalbum.model.Photo;
import org.photoalbum.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/photo")
@CrossOrigin
public class PhotoRestController {
    @Autowired
    private PhotoService photoService;

    @GetMapping
    public List<Photo> getAllPhotos(@RequestParam(name = "q") Optional<String> keyword){
        return photoService.getAll(keyword);
    }

    @GetMapping("/{id}")
    public Photo getPhotoById(@PathVariable Integer id){
        try {
           return photoService.getById(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping()
    public Photo create(@Valid @RequestBody Photo requestPhoto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was something wrong in your " +
                "request, please check that you provide all mandatory fields.");
        return photoService.storePhoto(requestPhoto);
    }

    @PutMapping("/{id}")
    public Photo update(@PathVariable Integer id, @Valid @RequestBody Photo requestPhoto, BindingResult bindingResult){
        if(bindingResult.hasErrors()) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There was something wrong in your " +
                "request, please check that you provide all mandatory fields.");

        try {
            return photoService.updatePhoto(requestPhoto, id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with Id: " + id + " not found");
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id){
        try {
            photoService.deletePhoto(id);
        } catch (PhotoNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Photo with Id: " + id + " not found");
        }
    }
}
