package com.alex.java.services;

import com.alex.java.dataObject.ProductInfo;
import com.alex.java.repo.ProductInfoRepo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.util.Assert;
import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductInfoServiceImplTest {

    @Autowired
    ProductInfoServiceImpl repo;

    @Test
    void save() {
        ProductInfo productInfo = new ProductInfo(
                "thisIsANewId",
                "Airpods",
                new BigDecimal("20.2"),
                1,
                "hao",
                "link",
                0,
                1);

        ProductInfo savedResult = repo.save(productInfo);

        assert savedResult != null;
    }

    @Test
    void findAll() {
        List<ProductInfo> productInfoList = repo.findAll();
        System.out.println(productInfoList.size());
        assert productInfoList.size() != 0;

    }

    @Test
    void findOneProductInfoById() {
        ProductInfo foundProductInfo = repo.findOneProductInfoById("1234");
        Assert.notNull(foundProductInfo, "测试数据不能为空");
    }

    @Test
    void findProductInfoByPage() {
        PageRequest pageRequest = PageRequest.of(0, 2);
        Page<ProductInfo> foundResult = repo.findProductInfoByPage(pageRequest);
        assert  foundResult.getContent().size() != 0;

    }


}