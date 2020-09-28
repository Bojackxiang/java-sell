package com.alex.java.services;

import com.alex.java.dataObject.ProductCategory;

import javax.swing.text.html.Option;
import org.springframework.data.domain.Pageable;;
import java.util.List;
import java.util.Optional;

public interface CategoryServiceInterface {

    Optional<ProductCategory> findById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);

}
