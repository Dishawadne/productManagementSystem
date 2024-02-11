package com.task.demo.Controller;

import com.task.demo.Entity.Product;
import com.task.demo.Service.CategoryService;
import com.task.demo.Service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
public class ProductControl {

    @Autowired
    private CategoryService cs;

    @Autowired
    private ProductService ps;
    
   

    private static final Logger log = LoggerFactory.getLogger(ProductControl.class);

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
       if (product.getCategory() != null && product.getCategory().getId() == null) {
           cs.createCategory(product.getCategory());
       }
        Product product1 =ps.createProduct(product);
        return product1;
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAllProducts() {
        try {
            List<Product> products = ps.getAllProducts();
            return new ResponseEntity<>(products, HttpStatus.OK);
        } catch (Exception e) {
            // Log the exception
            log.error("An error occurred while fetching products", e);

            // Return an appropriate error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable(value = "id") Long id) {
    	Product product = ps.getProductWithCategory(id);
       // return ps.getProductById(id);
    	return ResponseEntity.ok(product);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product product) {
        Product updatedProduct = ps.updateProduct(id, product);
        if (updatedProduct != null) {
            return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable(value = "id") Long id) {
        ps.deleteProduct(id);
        return ResponseEntity.ok().build();
    }


}
