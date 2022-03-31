package com.codegym.controller;

import com.codegym.model.Blog;
import com.codegym.service.IBlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/v1/blogs")
public class BlogRestController {
    @Autowired
    IBlogService blogService;

    @GetMapping
    public ResponseEntity<Page<Blog>> findAll(Pageable pageable){
        return new ResponseEntity<>(blogService.findAll(pageable), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> findOne(@PathVariable Long id){
        Optional<Blog> blog = blogService.findById(id);
        if (!blog.isPresent())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(blog.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Blog> save(@RequestBody Blog blog){
        return new ResponseEntity<>(blogService.save(blog), HttpStatus.OK);
    }


}
