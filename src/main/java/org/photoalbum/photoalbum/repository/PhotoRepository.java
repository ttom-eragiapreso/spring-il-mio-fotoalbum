package org.photoalbum.photoalbum.repository;

import org.photoalbum.photoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
}
