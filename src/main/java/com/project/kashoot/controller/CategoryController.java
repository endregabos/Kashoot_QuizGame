package com.project.kashoot.controller;


import com.project.kashoot.exception.ResourceNotFoundException;
import com.project.kashoot.model.Category;
import com.project.kashoot.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    //get categories
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("category")
    public List<Category> getAllCategories(){return this.categoryRepository.findAll();}

    // get category by id
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("category/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable(value = "id") Long category_id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + category_id));

        return ResponseEntity.ok().body(category);
    }

    //save category
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("category")
    public Category createCategory(@RequestBody Category category){return this.categoryRepository.save(category);}

    //update category
    @CrossOrigin(origins = "http://localhost:8080")
    @PutMapping("category/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable(value = "id") Long category_id, @Validated @RequestBody Category categoryDetails) throws ResourceNotFoundException{
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + category_id));

        category.setCategory_title(categoryDetails.getCategory_title());

        return ResponseEntity.ok(this.categoryRepository.save(category));
    }

    //delete category
    @CrossOrigin(origins = "http://localhost:8080")
    @DeleteMapping("category/{id}")
    public Map<String, Boolean> deleteCategory(@PathVariable(value = "id") Long category_id) throws ResourceNotFoundException {
        Category category = categoryRepository.findById(category_id).orElseThrow(() -> new ResourceNotFoundException("Category not found for this id :: " + category_id));

        this.categoryRepository.delete(category);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);

        return response;
    }

}
