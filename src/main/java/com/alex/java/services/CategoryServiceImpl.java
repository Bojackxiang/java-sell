package com.alex.java.services;

import com.alex.java.dataObject.ProductCategory;
import com.alex.java.repo.ProductCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryServiceInterface {

    @Autowired
    private ProductCategoryRepo productCategoryRepo;

    @Override
    public Optional<ProductCategory> findById(Integer categoryId) {

        return productCategoryRepo.findById(categoryId);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepo.findAll();
    }

    @Override
    public List<ProductCategory> findCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepo.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);
    }
}
