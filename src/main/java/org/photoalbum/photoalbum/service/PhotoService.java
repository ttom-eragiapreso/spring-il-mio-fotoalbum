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

    public List<Photo> getAllVisible(Optional<String> keyword){
        if(keyword.isEmpty()) return photoRepository.findByVisibleTrue();
        return photoRepository.findByTitleContainingIgnoreCaseAndVisibleTrue(keyword.get());
    }

    public Photo getById(Integer id) throws PhotoNotFoundException{
        return photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Photo with id " + id + " not found"));
    }

    public Photo getByIdApi(Integer id) throws PhotoNotFoundException{
        return photoRepository.findByIdAndVisibleTrue(id).orElseThrow(()-> new PhotoNotFoundException("Photo with id " + id + " not found"));
    }

    public void deletePhoto(Integer id) throws PhotoNotFoundException{
        Photo photoToDelete = photoRepository.findById(id).orElseThrow(() -> new PhotoNotFoundException("Photo with id " + id + " not found"));
        photoRepository.delete(photoToDelete);
    }

    public Photo updatePhoto(Photo formPhoto, Integer id) throws PhotoNotFoundException{
        Photo photoToUpdate = getById(id);

        photoToUpdate.setVisible(formPhoto.getVisible());
        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setCategories(formPhoto.getCategories());
        photoToUpdate.setUrl(formPhoto.getUrl());

       return photoRepository.save(photoToUpdate);
    }

    public Photo updatePhotoApi(Photo formPhoto, Integer id) throws PhotoNotFoundException{
        Photo photoToUpdate = getById(id);

        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setCategories(formPhoto.getCategories());
        photoToUpdate.setUrl(formPhoto.getUrl());

        return photoRepository.save(photoToUpdate);
    }

    public Photo storePhoto(Photo formPhoto){
        Photo photoToCreate = new Photo();

        photoToCreate.setUrl(formPhoto.getUrl());
        photoToCreate.setTitle(formPhoto.getTitle());
        photoToCreate.setDescription(formPhoto.getDescription());
        photoToCreate.setCategories(formPhoto.getCategories());
        photoToCreate.setVisible(formPhoto.getVisible());

        return photoRepository.save(photoToCreate);
    }
}
