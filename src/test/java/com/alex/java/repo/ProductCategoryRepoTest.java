package com.alex.java.repo;

import com.alex.java.dataObject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;


@SpringBootTest
class ProductCategoryRepoTest {
    @Autowired
    private ProductCategoryRepo repo;


    @Test
    public void findOneTest(){
        Optional<ProductCategory> result = repo.findById(1);
        System.out.println(result.toString());
    }


}