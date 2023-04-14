package org.photoalbum.photoalbum.api;

import org.photoalbum.photoalbum.exception.PhotoNotFoundException;
import org.photoalbum.photoalbum.model.Photo;
import org.photoalbum.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
}
