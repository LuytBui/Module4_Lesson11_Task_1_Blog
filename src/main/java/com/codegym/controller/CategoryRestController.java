package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.model.Category;
import com.codegym.service.IBlogService;
import com.codegym.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/v1/categories")
public class CategoryRestController {
    @Autowired
    ICategoryService categoryService;

    @Autowired
    IBlogService blogService;

    @GetMapping
    public ResponseEntity<Page<Category>> findAll(Pageable pageable){
        return new ResponseEntity<>(categoryService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Page<Blog>> findOne(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        if (!category.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        Page<Blog> blogs = blogService.findAllByCategory(category.get(), Pageable.unpaged());
        return new ResponseEntity<>(blogs, HttpStatus.OK);
    }

}
