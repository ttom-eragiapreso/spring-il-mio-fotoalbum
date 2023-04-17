package org.photoalbum.photoalbum.service;

import org.photoalbum.photoalbum.exception.CategoryNotFoundException;
import org.photoalbum.photoalbum.model.Category;
import org.photoalbum.photoalbum.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Category getById(Integer id){
        return categoryRepository.findById(id).orElseThrow(() -> new CategoryNotFoundException("Category with Id " + id + " not found"));
    }

    public void deleteById(Integer id){
        Category categoryToDelete = getById(id);
        categoryRepository.delete(categoryToDelete);
    }

    public void update(Integer id, Category formCategory){
        Category categoryToUpdate = getById(id);
        categoryToUpdate.setName(formCategory.getName());
        categoryRepository.save(categoryToUpdate);
    }

    public void create(Category formCategory){
        Category cat = new Category();
        cat.setName(formCategory.getName());
        categoryRepository.save(cat);
    }
}
