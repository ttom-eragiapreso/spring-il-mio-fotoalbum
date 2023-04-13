package org.photoalbum.photoalbum.repository;

import org.photoalbum.photoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
