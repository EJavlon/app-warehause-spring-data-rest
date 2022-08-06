package com.company.appwarehausespringdatarest.service;

import com.company.appwarehausespringdatarest.entity.Category;
import com.company.appwarehausespringdatarest.payload.Result;
import com.company.appwarehausespringdatarest.repasitory.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    CategoryRepository categoryRepository;

    public Result addCategory(Category category) {
        boolean exists = categoryRepository.existsByName(category.getName());
        if (exists) return new Result("There is such a category",false);

        categoryRepository.save(category);
        return new Result("Category seccessfully added",true);
    }

    public Result getCategoryById(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new Result("There is such a category",false);

        boolean exists = categoryRepository.existsByParentCategory(optionalCategory.get().getParentCategory());
        if (!exists) return new Result("Parend category not found", false);

        return new Result("Category found",true,optionalCategory.get());
    }

    public Page<Category> getCategorys(int page) {
        Pageable pageable = PageRequest.of(page,10);
        return categoryRepository.findAll(pageable);
    }

    public Result editCategory(Integer id, Category category) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new Result("Category not found",false);

        category.setId(optionalCategory.get().getId());
        return new Result("Category seccessfully edited",true,category);
    }

    public Result deleteCategory(Integer id) {
        Optional<Category> optionalCategory = categoryRepository.findById(id);
        if (!optionalCategory.isPresent()) return new Result("Category not found",false);

        categoryRepository.deleteById(id);
        return new Result("Category seccessfully deleted",true);
    }
}
