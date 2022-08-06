package com.company.appwarehausespringdatarest.controller;

import com.company.appwarehausespringdatarest.entity.Category;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public Result addCategory(@RequestBody Category category){
        return categoryService.addCategory(category);
    }

    @GetMapping("/{id}")
    public Result getCategoryById(@PathVariable Integer id){
        return categoryService.getCategoryById(id);
    }

    @GetMapping
    public Page<Category> getCategorys(@RequestParam int page){
        return categoryService.getCategorys(page);
    }

    @PutMapping("/{id}")
    public Result editCategory(@PathVariable Integer id, @RequestBody Category category){
        return categoryService.editCategory(id,category);
    }

    @DeleteMapping("/{id}")
    public Result deleteCategory(@PathVariable Integer id){
        return categoryService.deleteCategory(id);
    }

}
