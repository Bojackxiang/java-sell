package com.alex.java.repo;

import com.alex.java.dataObject.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductCategoryRepo extends JpaRepository<ProductCategory, Integer> {


}
