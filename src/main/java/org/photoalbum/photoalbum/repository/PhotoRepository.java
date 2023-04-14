package org.photoalbum.photoalbum.repository;

import org.photoalbum.photoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {

    public List<Photo> findByTitleContainingIgnoreCase(String keyword);

    public List<Photo> findByTitleContainingIgnoreCaseAndVisibleTrue(String keyword);

    public List<Photo> findByVisibleTrue();

    public Optional<Photo> findByIdAndVisibleTrue(Integer id);

}
