package com.alex.java.services;

import com.alex.java.dataObject.ProductInfo;
import com.alex.java.repo.ProductInfoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoRepo productInfoRepo;

    @Test
    void findById() {
        Optional<ProductInfo> productInfo = productInfoRepo.findById("123");
        System.out.println(productInfo.toString());

    }
}