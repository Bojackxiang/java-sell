package com.alex.java.repo;

import com.alex.java.dataObject.ProductCategory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SpringBootTest
class ProductCategoryRepoTest {
    @Autowired
    private ProductCategoryRepo repo;

    @Test
    public void findOneTest() {
        Optional<ProductCategory> result = repo.findById(1);
        System.out.println(result.toString());
    }

    @Test
    @Transactional
    public void saveOneTest() {
        ProductCategory productCategory = new ProductCategory("名称8", 1);
        ProductCategory saveResult = repo.save(productCategory);
        System.out.println(saveResult.toString());

        Assert.notNull(saveResult, "存储结果不能是 null");
    }

    @Test
    public void updateOne() {

//        Optional<ProductCategory> findResult = repo.findById(3);
//        boolean isOptional = findResult.isPresent();
//        if (!isOptional) {
//            System.out.println("hello");
//        } else {
//            ProductCategory productCategory;
//            productCategory = findResult.get();
//            productCategory.setCategoryName("ae86");
//            repo.save(productCategory);
//        }

    }

    @Test
    public void retriveList(){
        List<Integer> cateList = Arrays.asList(1, 2);

        List<ProductCategory> findResult = repo.findByCategoryTypeIn(cateList);
        for(ProductCategory productCategory: findResult){
            System.out.println(productCategory.getCategoryName());
        }
        Assert.notEmpty(findResult, "findResult 不能为空");
    }

}