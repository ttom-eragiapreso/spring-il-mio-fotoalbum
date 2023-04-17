package org.photoalbum.photoalbum.controller;

import jakarta.validation.Valid;
import org.photoalbum.photoalbum.model.Category;
import org.photoalbum.photoalbum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        model.addAttribute("category", new Category());
        return "/categories/index";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return "redirect:/category";
    }
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        Category categoryToUpdate = categoryService.getById(id);
        model.addAttribute("category", categoryToUpdate);
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/categories/index";
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute Category formCategory, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/categories/index";
        }
        categoryService.update(id, formCategory);
        return "redirect:/category";
    }

    @PostMapping("/create")
    public String create(@Valid @ModelAttribute Category formCategory, BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            model.addAttribute("categories", categoryService.getAllCategories());
            return "/categories/index";
        }
        categoryService.create(formCategory);
        return "redirect:/category";
    }
}
