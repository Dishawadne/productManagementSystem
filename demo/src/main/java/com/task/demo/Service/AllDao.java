package com.task.demo.Service;

import com.task.demo.Entity.Category;
import com.task.demo.Entity.Product;
import com.task.demo.repo.CategoryRepo;
import com.task.demo.repo.ProductRepo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AllDao implements CategoryService,ProductService{

    @Autowired
    private CategoryRepo repo;

    @Autowired
    private ProductRepo repo1;
    
//    public Page<Category> getAllCategories(Pageable pageable) {
//        return repo.findAll(pageable);
//    }
//    
//    public Page<Product> getAllProducts(Pageable pageable) {
//        return repo1.findAll(pageable);
//    }
    public Product getProductWithCategory(Long id) {
    	    
    	    
    	      
        Optional<Product> productOptional = repo1.findById(id);
        if (productOptional.isPresent()) {
            return productOptional.get();
        }
        throw new EntityNotFoundException("Product not found with id: " + id);
    }
    

    @Override
    public List<Category> getAllCategories() {
        return repo.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return repo.findById(id).orElseThrow();
    }

    @Override
    public Category createCategory(Category category) {
        return repo.save(category);
    }
//    @Transactional
//    @Override
//    public Category updateCategory(Long id, Category newCategory) {
//        if (repo1.existsById(id)) {
//            newCategory.setId(id);
//
//            return repo.save(newCategory);
//        } else {
//            return null;
//        }
//    }

    @Override
    public void deleteCategory(Long id) {
        Category category = getCategoryById(id);
        repo.delete(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return repo1.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return repo1.findById(id).orElseThrow();
    }

    @Override
    public Product createProduct(Product product) {
        return repo1.save(product);
    }

    
    @Override
    public Product updateProduct(Long id, Product newProduct) {
        Optional<Product> productOptional = repo1.findById(id);
        if (productOptional.isPresent()) {
            Product existingProduct = productOptional.get();
            existingProduct.setName(newProduct.getName());
            existingProduct.setPrice(newProduct.getPrice());
            
            return repo1.save(existingProduct);
        } else {
            return null;
        }
    }



    @Override
    public void deleteProduct(Long id) {

        Product product = getProductById(id);
        repo1.delete(product);

    }
    
    @Transactional
    @Override
    public Category updateCategory(Long id, Category newCategory) {
        Category existingCategory = repo.findById(newCategory.getId()).orElse(null);
        if (existingCategory != null) {
            
            existingCategory.setName(newCategory.getName());
            
            return repo.save(existingCategory);
        } else {
            
            return null;
        }
    }

	
    
   
//	@Override
//    public Page<Product> getAllProducts(Pageable pageable) {
//        return repo1.findAll(pageable);
//    }
}
