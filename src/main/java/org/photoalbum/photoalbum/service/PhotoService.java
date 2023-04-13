package org.photoalbum.photoalbum.service;

import org.photoalbum.photoalbum.exception.PhotoNotFoundException;
import org.photoalbum.photoalbum.model.Category;
import org.photoalbum.photoalbum.model.Photo;
import org.photoalbum.photoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAll(Optional<String> keyword){
        if(keyword.isEmpty()) return photoRepository.findAll();
        return photoRepository.findByTitleContainingIgnoreCase(keyword.get());
    }

    public Photo getById(Integer id) throws PhotoNotFoundException{
        return photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Photo with id " + id + " not found"));
    }

    public void deletePhoto(Integer id) throws PhotoNotFoundException{
        Photo photoToDelete = photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Photo with id " + id + " not found"));
        photoRepository.delete(photoToDelete);
    }

    public void updatePhoto(Photo formPhoto, Integer id){
        Photo photoToUpdate = getById(id);

        photoToUpdate.setVisible(formPhoto.getVisible());
        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setCategories(formPhoto.getCategories());
        photoToUpdate.setUrl(formPhoto.getUrl());

        photoRepository.save(photoToUpdate);
    }
}
