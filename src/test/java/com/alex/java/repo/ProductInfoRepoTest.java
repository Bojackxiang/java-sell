package com.alex.java.repo;

import com.alex.java.dataObject.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductInfoRepoTest {

    @Autowired
    ProductInfoRepo productInfoRepo;

    @Test
    void findByProductId() {
        Optional<ProductInfo> productInfo = productInfoRepo.findById("1234");
        System.out.println(productInfo);

    }

    @Test
    void findByProductStatus() {
    }

    @Test
    void saveProductInfo() {
        ProductInfo productInfo = new ProductInfo(
                "1234",
                "mouse",
                new BigDecimal("20.2"),
                1,
                "hao",
                "link",
                0,
                1);
        System.out.println(productInfo.toString());

        ProductInfo savedData = productInfoRepo.save(productInfo);

        System.out.println(savedData.toString());

//        System.out.println(savedData.toString());
    }

    @Test
    void findProductByItsId(){
        Optional<ProductInfo> productInfo = productInfoRepo.findProductInfosByProductId("1234");
        System.out.println(productInfo.toString());
    }
}