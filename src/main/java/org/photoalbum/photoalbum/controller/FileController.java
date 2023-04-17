package org.photoalbum.photoalbum.controller;

import org.photoalbum.photoalbum.model.Photo;
import org.photoalbum.photoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FileController {
    @Autowired
    private PhotoService photoService;


    @GetMapping("/{photoId}")
    public ResponseEntity<byte[]> serveImage(@PathVariable Integer photoId){
        Photo photo = photoService.getById(photoId);

        MediaType mediaType = MediaType.IMAGE_JPEG;
        return ResponseEntity.ok().contentType(mediaType).body(photo.getFile());
    }
}
