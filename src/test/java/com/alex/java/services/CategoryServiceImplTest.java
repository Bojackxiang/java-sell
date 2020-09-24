package com.alex.java.services;

import com.alex.java.dataObject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.beans.Transient;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class CategoryServiceImplTest {
    @Autowired
    private CategoryServiceImpl categoryService;

    @Test
    void findById() {
        Optional<ProductCategory> productCategoryOptional = categoryService.findById(1);
        ProductCategory productCategory = productCategoryOptional.orElse(null);
        Assert.notNull(productCategory, "Production 不能是null");

        // assert productCategory != null;

    }

    @Test
    void findAll() {
        List<ProductCategory> productCategories = categoryService.findAll();
        Integer length = productCategories.size();
        System.out.println(length);

        assert productCategories.size() != 0;
    }

    @Test
    void findCategoryTypeIn() {
        List<Integer> cateType = Arrays.asList(0,1);
        List<ProductCategory> productCategoriesList = categoryService.findCategoryTypeIn(cateType);
        assert productCategoriesList.size() != 0;
    }


    @Test
    void save() {
        ProductCategory sample = new ProductCategory("a ", 9);
        ProductCategory productCategory = categoryService.save(sample);
        assert productCategory != null;
    }
}