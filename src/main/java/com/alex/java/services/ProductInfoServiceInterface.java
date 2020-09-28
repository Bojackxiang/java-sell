package com.alex.java.services;

import com.alex.java.dataObject.ProductInfo;
import org.springframework.data.domain.Page;

import org.springframework.data.domain.Pageable;
import java.util.List;

public interface ProductInfoServiceInterface {

    ProductInfo save(ProductInfo productInfo);

    List<ProductInfo> findAll();

    ProductInfo findOneProductInfoById(String id);

    Page<ProductInfo> findProductInfoByPage(Pageable pageable);

    // ProductInfo increaseStocking();

    // ProductInfo decreaseStocking();




}