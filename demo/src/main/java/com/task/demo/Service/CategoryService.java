package com.task.demo.Service;

import com.task.demo.Entity.Category;
import com.task.demo.Entity.Product;
import com.task.demo.repo.CategoryRepo;
import com.task.demo.repo.ProductRepo;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    public List<Category> getAllCategories();

    public Category getCategoryById(Long id);

    public Category createCategory(Category category);

    public Category updateCategory(Long id, Category categoryDetails);

    public void deleteCategory(Long id);
    

    

    
   
}
    
    

