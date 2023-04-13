package org.photoalbum.photoalbum.service;

import org.photoalbum.photoalbum.model.Photo;
import org.photoalbum.photoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;

    public List<Photo> getAll(){
        return photoRepository.findAll();
    }
}
