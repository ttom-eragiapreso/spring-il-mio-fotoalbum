package org.photoalbum.photoalbum.controller;

import org.photoalbum.photoalbum.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public String index(Model model){
        model.addAttribute("categories", categoryService.getAllCategories());
        return "/categories/index";
    }
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id){
        categoryService.deleteById(id);
        return "redirect:/category";
    }
}
