package com.alex.java.repo;

import com.alex.java.dataObject.ProductInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class ProductInfoRepoTest {

    @Autowired
    ProductInfoRepo productInfoRepo;

    @Test
    void findByProductId() {
        Optional<ProductInfo> productInfo = productInfoRepo.findProductInfosByProductId("1");
        System.out.println(productInfo);
        Assert.notNull(productInfo, "测试的数据不能为 null");

    }

    @Test
    void findByProductStatus() {
        List<ProductInfo> productInfo = productInfoRepo.findByProductStatus(0);
        assert productInfo.size() != 0;
    }

    @Test
    void saveProductInfo() {
        ProductInfo productInfo = new ProductInfo(
                "123",
                "keyboard",
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
    void findProductByItsId() {
        Optional<ProductInfo> productInfo = productInfoRepo.findProductInfosByProductId("1234");
        System.out.println(productInfo.toString());
    }
}