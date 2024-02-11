package com.task.demo.repo;

import com.task.demo.Entity.Category;
import com.task.demo.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long> {

	Category saveAll(Category newCategory);


}
